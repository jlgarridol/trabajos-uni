<%@ Page Title="Contact" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Contact.aspx.cs" Inherits="Contact" %>
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
        <h1><asp:Label ID="Label1" runat="server" Text="<%$ Resources:Título %>"></asp:Label></h1>
    </hgroup>

    <section class="contact">
        <header>
            <h3><asp:Label ID="Label2" runat="server" Text="<%$ Resources:subtítulo1 %>"></asp:Label></h3>
        </header>
        <p>
            <span class="label"><asp:Label ID="Label3" runat="server" Text="<%$ Resources:contacto1 %>"></asp:Label></span>
            <span>947 00 00 00</span>
        </p>
        <p>
            <span class="label"><asp:Label ID="Label4" runat="server" Text="<%$ Resources:contacto2 %>"></asp:Label></span>
            <span>oficinas@acme.com</span>
        </p>
    </section>

    <section class="contact">
        <header>
            <h3><asp:Label ID="Label5" runat="server" Text="<%$ Resources:subtítulo2 %>"></asp:Label></h3>
        </header>
        <p>
            <span class="label"><asp:Label ID="Label6" runat="server" Text="<%$ Resources:equip %>"></asp:Label></span>
            <span><a href="mailto:jgl0062@alu.ubu.es">jgl0062@alu.ubu.es</a></span>
            <span><a href="mailto:jrs0070@alu.ubu.es">jrs0070@alu.ubu.es</a></span>
            <span><a href="mailto:ezl0003@alu.ubu.es">ezl0003@alu.ubu.es</a></span>
        </p>
    </section>

    <section class="contact">
        <header>
            <h3><asp:Label ID="Label7" runat="server" Text="<%$ Resources:subtítulo3 %>"></asp:Label></h3>
        </header>
        <p>
            <asp:Label ID="Label8" runat="server" Text="<%$ Resources:direccion1 %>"></asp:Label><br />
            <asp:Label ID="Label9" runat="server" Text="<%$ Resources:direccion2 %>"></asp:Label>
        </p>
    </section>
</asp:Content>