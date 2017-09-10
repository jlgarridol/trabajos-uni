<%@ Page Title="Profile" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Profile.aspx.cs" Inherits="About" %>
<script runat="server">
    protected override void InitializeCulture()
    {
        if (DBPruebas.CIA != null)
        {
            Usuario logueado = DBPruebas.getUsuario();
            UICulture = logueado.getLang();
            Culture = logueado.getCulture();
        }
        base.InitializeCulture();
    }
</script>
<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <% 
        if (Request.Form["ctl00$MainContent$nameForm"] != null)
        {
            String name = Request.Form["ctl00$MainContent$nameForm"];
            String apellido = Request.Form["ctl00$MainContent$apellidoForm"];
            String apellido2 = Request.Form["ctl00$MainContent$apellido2Form"];
            String nif = Request.Form["ctl00$MainContent$nifForm"];
            String mail = Request.Form["ctl00$MainContent$mailForm"];
            String iban = Request.Form["ctl00$MainContent$ibanForm"];
            String pan = Request.Form["ctl00$MainContent$panForm"];
            String pass = Request.Form["ctl00$MainContent$passForm"];

            Dictionary<String, String> enviar = new Dictionary<String, String>();
            if (name != null && name != "")
                enviar.Add("nombre", name);
            if (apellido != null && apellido != "")
                enviar.Add("apellido1", apellido);
            if(apellido2 != null && apellido2 != "")
                enviar.Add("apellido2", apellido2);
            if (nif != null && nif != "")
                enviar.Add("nif", nif);
            if (iban != null && iban != "")
                enviar.Add("IBAN", iban);
            if (pan != null && pan != "")
                enviar.Add("PAN", pan);
            if (pass != null && pass != "")
                enviar.Add("contrasena", pass);
            if (mail != null && mail != "")
                enviar.Add("email", mail);
            DBPruebas.ModifyUser(enviar);
        }
     %>
        <div class="cuarto"><p><asp:Label ID="Label" runat="server" Text="<%$ Resources:nombre %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato1"><%: DBPruebas.getUsuario().getNombre()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="nameForm" runat="server"></asp:TextBox></div>
        <div class="cuarto"><p><asp:Label ID="Label1" runat="server" Text="<%$ Resources:ape1 %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato2"><%: DBPruebas.getUsuario().getApellido1()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="apellidoForm" runat="server"> </asp:TextBox></div>
        <% if(DBPruebas.getUsuario().getApellido2()!=""){ %>
        <div class="cuarto"><p><asp:Label ID="Label2" runat="server" Text="<%$ Resources:ape2 %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato3"><%: DBPruebas.getUsuario().getApellido2()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="apellido2Form" runat="server"> </asp:TextBox></div>
        <%} %>
        <div class="cuarto"><p><asp:Label ID="Label3" runat="server" Text="<%$ Resources:NIF %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato4"><%: DBPruebas.getUsuario().getNIF()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="nifForm" runat="server" > </asp:TextBox></div>
        <div class="cuarto"><p><asp:Label ID="Label4" runat="server" Text="<%$ Resources:correo %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato5"><%: DBPruebas.getUsuario().getEmail()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="mailForm" runat="server" ></asp:TextBox></div>
        <div class="cuarto"><p><asp:Label ID="Label5" runat="server" Text="<%$ Resources:IBAN %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato6"><%: DBPruebas.getUsuario().getIBAN()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="ibanForm" runat="server" ></asp:TextBox></div>
        <div class="cuarto"><p><asp:Label ID="Label6" runat="server" Text="<%$ Resources:PAN %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato7"><%: DBPruebas.getUsuario().getPAN()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="panForm" runat="server"> </asp:TextBox></div>
        <div class="cuarto"><p><asp:Label ID="Label7" runat="server" Text="<%$ Resources:contra %>"></asp:Label></p></div>
        <div class="trescuarto primeravez"><p id="dato8"><%: DBPruebas.getUsuario().getContrasena()%></p></div><div class="trescuarto segundavez"><asp:TextBox ID="passForm" runat="server"></asp:TextBox></div>
    <asp:Button ID="button" Text="<%$ Resources:mod %>" UseSubmitBehavior="true" CssClass="clear" OnClientClick="boton()" runat="server"/>
    <asp:Button ID="button1" Text="<%$ Resources:confirm %>" UseSubmitBehavior="false" CssClass="clear" runat="server" />

    <script>
        function todo() {
            var primeravez = document.getElementsByClassName("primeravez");
            var segundavez = document.getElementsByClassName("segundavez");
            for (var i = 0; i < primeravez.length; i++) {
                segundavez[i].firstChild.value = primeravez[i].firstChild.innerHTML;
                segundavez[i].style.height = primeravez[i].offsetHeight + ".2px";
            }
            document.getElementById("MainContent_button1").style.display = "none";
            document.getElementById("MainContent_button").type = "button";
        }
        todo();
        function boton() {
            
            var primeravez = document.getElementsByClassName("primeravez");
            var segundavez = document.getElementsByClassName("segundavez");
            for (var i = 0; i < primeravez.length; i++) {
                
                segundavez[0].className = "trescuarto";
                primeravez[i].style.display = "none";
            }
            document.getElementById("MainContent_button").style.display = "none";
            document.getElementById("MainContent_button1").style.display = "";
        }
    </script>
    
</asp:Content>