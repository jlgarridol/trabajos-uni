<%@ Page Title="Iniciar sesión" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Account_Login" %>
<%@ Register Src="~/Account/OpenAuthProviders.ascx" TagPrefix="uc" TagName="OpenAuthProviders" %>
<script runat="server">
    protected override void InitializeCulture()
    {
        if (DBPruebas.CIA != null)
        {
            Usuario logueado = DBPruebas.getUsuario();
            UICulture = logueado.getLang();
            Culture = logueado.getCulture();
        }
        else
        {
            UICulture = "es-ES";
            Culture = "es";
        }
        base.InitializeCulture();
    }
</script>
<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <%
        int situacion=0;
        String cia = Request.Form["ctl00$MainContent$cia"];
        if (cia != null)
        {
            String pass = Request.Form["ctl00$MainContent$pass"];
            String email = Request.Form["ctl00$MainContent$email"];
            switch (DBPruebas.checkUser(cia, pass, email))
            {
                case 0:
                    DBPruebas.setCIA(cia);
                    Response.Redirect("/");
                    break;
                case 1:
                    situacion = 1;
                    break;
                default:
                    situacion = 2;
                    break;
            }
        }
             %>
    <table>
        <% if(situacion==1){ %>
        <tr>
            <th colspan="2" style="color:red">Error: Usuario desconocido</th>
        </tr>
        <% }else if(situacion==2){ %>
         <tr>
            <th colspan="2" style="color:red">Error: Combinación usuario contraseña incorrecta</th>
        </tr>
        <% } %>
        <tr>
            <th colspan="2">Introduzca sus credenciales </th>
        </tr>
        <tr>
            <th>CIA:</th>
            <td><asp:TextBox ID="cia" runat="server"></asp:TextBox></td>
        </tr>
        <tr>
            <th>Email:</th>
            <td><asp:TextBox ID="email" runat="server"></asp:TextBox></td>
        </tr>
        <tr>
            <th>Contraseña:</th>
            <td><asp:TextBox TextMode="password" ID="pass" runat="server"></asp:TextBox></td>
        </tr>
        <tr>
            <td colspan="2"><asp:Button ID="button" runat="server" Text="Subir" /></td>
        </tr>
    </table>
</asp:Content>