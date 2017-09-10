<%@ Page Title="About" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="About.aspx.cs" Inherits="About" %>
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
    <hgroup class="title">
        <h1><asp:Label ID="Label1" runat="server" Text="<%$ Resources:Who %>"></asp:Label></h1>
    </hgroup>

    <article>
        <p>        
            <asp:Label ID="Label2" runat="server" Text="<%$ Resources:LongText %>"></asp:Label>
        </p>
    </article>
</asp:Content>