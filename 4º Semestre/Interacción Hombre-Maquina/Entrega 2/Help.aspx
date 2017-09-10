<%@ Page Title="Help" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Help.aspx.cs" Inherits="About" %>
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
    <h1><asp:Label runat="server" Text="<%$ Resources:Title %>"></asp:Label></h1>
    <div>
        <h2><asp:Label ID="Label1" runat="server" Text="<%$ Resources:Inicio %>"></asp:Label></h2>
        <p><asp:Label ID="Label2" runat="server" Text="<%$ Resources:InicioInfo %>"></asp:Label></p>
        <h2><asp:Label ID="Label3" runat="server" Text="<%$ Resources:Perfil %>"></asp:Label></h2>
        <p><asp:Label ID="Label4" runat="server" Text="<%$ Resources:PerfilInfo %>"></asp:Label></p>
        <h2><asp:Label ID="Label5" runat="server" Text="<%$ Resources:Coches %>"></asp:Label></h2>
        <p><asp:Label ID="Label6" runat="server" Text="<%$ Resources:CochesInfo %>"></asp:Label></p>
        <h3><asp:Label ID="Label7" runat="server" Text="<%$ Resources:Ticket %>"></asp:Label></h3>
        <p><asp:Label ID="Label8" runat="server" Text="<%$ Resources:TicketsActuales %>"></asp:Label></p>
        <h3><asp:Label ID="Label9" runat="server" Text="<%$ Resources:Infraccion %>"></asp:Label></h3>
        <p><asp:Label ID="Label10" runat="server" Text="<%$ Resources:Infracciones %>"></asp:Label></p>
        <h3><asp:Label ID="Label11" runat="server" Text="<%$ Resources:Coche %>"></asp:Label></h3>
        <p><asp:Label ID="Label12" runat="server" Text="<%$ Resources:CocheInfo %>"></asp:Label></p>
        <h2><asp:Label ID="Label13" runat="server" Text="<%$ Resources:Sobre %>"></asp:Label></h2>
        <p><asp:Label ID="Label14" runat="server" Text="<%$ Resources:SobreInfo %>"></asp:Label></p>
        <h2><asp:Label ID="Label15" runat="server" Text="<%$ Resources:Contacto %>"></asp:Label></h2>
        <p><asp:Label ID="Label16" runat="server" Text="<%$ Resources:ContactoInfo %>"></asp:Label></p>
    </div>
</asp:Content>