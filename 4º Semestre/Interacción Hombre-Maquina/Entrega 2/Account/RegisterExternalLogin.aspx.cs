using System;
using System.Web;
using System.Web.Security;
using DotNetOpenAuth.AspNet;
using Microsoft.AspNet.Membership.OpenAuth;

public partial class Account_RegisterExternalLogin : System.Web.UI.Page
{
    protected string ProviderName
    {
        get { return (string)ViewState["ProviderName"] ?? String.Empty; }
        private set { ViewState["ProviderName"] = value; }
    }

    protected string ProviderDisplayName
    {
        get { return (string)ViewState["ProviderDisplayName"] ?? String.Empty; }
        private set { ViewState["ProviderDisplayName"] = value; }
    }

    protected string ProviderUserId
    {
        get { return (string)ViewState["ProviderUserId"] ?? String.Empty; }
        private set { ViewState["ProviderUserId"] = value; }
    }

    protected string ProviderUserName
    {
        get { return (string)ViewState["ProviderUserName"] ?? String.Empty; }
        private set { ViewState["ProviderUserName"] = value; }
    }

    protected void Page_Load()
    {
        if (!IsPostBack)
        {
            ProcessProviderResult();
        }
    }

    protected void logIn_Click(object sender, EventArgs e)
    {
        CreateAndLoginUser();
    }

    protected void cancel_Click(object sender, EventArgs e)
    {
        RedirectToReturnUrl();
    }

    private void ProcessProviderResult()
    {
        // Procesar el resultado de un proveedor de autenticación en la solicitud
        ProviderName = OpenAuth.GetProviderNameFromCurrentRequest();

        if (String.IsNullOrEmpty(ProviderName))
        {
            Response.Redirect(FormsAuthentication.LoginUrl);
        }

        ProviderDisplayName = OpenAuth.GetProviderDisplayName(ProviderName);

        // Crear la dirección URL de redirección para la comprobación de OpenAuth
        var redirectUrl = "~/Account/RegisterExternalLogin";
        var returnUrl = Request.QueryString["ReturnUrl"];
        if (!String.IsNullOrEmpty(returnUrl))
        {
            redirectUrl += "?ReturnUrl=" + HttpUtility.UrlEncode(returnUrl);
        }

        // Comprobar la carga de OpenAuth
        var authResult = OpenAuth.VerifyAuthentication(redirectUrl);
        if (!authResult.IsSuccessful)
        {
            Title = "Error de inicio de sesión externo";
            userNameForm.Visible = false;
            
            ModelState.AddModelError("Provider", String.Format("Error de inicio de sesión externo {0}.", ProviderDisplayName));
            
            // Para ver este error, habilite el seguimiento de página en web.config (<system.web><trace enabled="true"/></system.web>) y visite ~/Trace.axd
            Trace.Warn("OpenAuth", String.Format("Error al comprobar la autenticación con {0})", ProviderDisplayName), authResult.Error);
            return;
        }

        // El usuario ha iniciado sesión con el proveedor correctamente
        // Comprobar si el usuario ya está registrado localmente
        if (OpenAuth.Login(authResult.Provider, authResult.ProviderUserId, createPersistentCookie: false))
        {
            RedirectToReturnUrl();
        }

        // Almacenar los detalles del proveedor en ViewState
        ProviderName = authResult.Provider;
        ProviderUserId = authResult.ProviderUserId;
        ProviderUserName = authResult.UserName;

        // Seccionar la cadena de consulta desde la acción
        Form.Action = ResolveUrl(redirectUrl);

        if (User.Identity.IsAuthenticated)
        {
            // El usuario ya se ha autenticado, agregue el inicio de sesión externo y redirija para volver a la dirección URL
            OpenAuth.AddAccountToExistingUser(ProviderName, ProviderUserId, ProviderUserName, User.Identity.Name);
            RedirectToReturnUrl();
        }
        else
        {
            // El usuario es nuevo, solicitar nombres de pertenencia deseados
            userName.Text = authResult.UserName;
        }
    }

    private void CreateAndLoginUser()
    {
        if (!IsValid)
        {
            return;
        }

        var createResult = OpenAuth.CreateUser(ProviderName, ProviderUserId, ProviderUserName, userName.Text);
        if (!createResult.IsSuccessful)
        {
            
                ModelState.AddModelError("UserName", createResult.ErrorMessage);
                
        }
        else
        {
            // Creación y asociación de usuario correctas
            if (OpenAuth.Login(ProviderName, ProviderUserId, createPersistentCookie: false))
            {
                RedirectToReturnUrl();
            }
        }
    }

    private void RedirectToReturnUrl()
    {
        var returnUrl = Request.QueryString["ReturnUrl"];
        if (!String.IsNullOrEmpty(returnUrl) && OpenAuth.IsLocalUrl(returnUrl))
        {
            Response.Redirect(returnUrl);
        }
        else
        {
            Response.Redirect("~/");
        }
    }
}