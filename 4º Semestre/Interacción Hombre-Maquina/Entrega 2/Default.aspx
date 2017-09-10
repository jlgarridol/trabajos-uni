<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" meta:resourcekey="Default"%>
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
<asp:Content runat="server" ID="FeaturedContent" ContentPlaceHolderID="FeaturedContent">
    <section class="featured">
        <div class="content-wrapper">
            <hgroup class="title">
                <% if (DBPruebas.CIA == null)
                   { %>
                    <h2><asp:Label ID="Label1" runat="server" Text="<%$ Resources:Titulo1 %>"></asp:Label><br /></h2>
                    <h3><asp:Label ID="Label2" runat="server" Text="<%$ Resources:Titulo2 %>"></asp:Label></h3>
                <% }
                   else
                   { %>
                    <h2><asp:Label ID="Label3" runat="server" Text="<%$ Resources:Titulo3 %>"></asp:Label> <%: DBPruebas.getUsuario().getNombre() + " " + DBPruebas.getUsuario().getApellido1() + " " + DBPruebas.getUsuario().getApellido2()%></h2>
                <% } %>
            </hgroup>
            <div>
                <% if (DBPruebas.CIA == null) {%>
                <div><p><asp:Label ID="Label4" runat="server" Text="<%$ Resources:Null1 %>"></asp:Label></p></div>
                <%} else{ %>
                <div><p><asp:Label ID="Label5" runat="server" Text="<%$ Resources:NoNull1 %>"></asp:Label> <%: DBPruebas.getUsuario().getVehiculos().Count %> <asp:Label ID="Label7" runat="server" Text="<%$ Resources:NoNull2 %>"></asp:Label> <%: DBPruebas.getUsuario().getTickets().Count %> <asp:Label ID="Label6" runat="server" Text="<%$ Resources:NoNull3 %>"></asp:Label></p></div>
                <% } %>
            </div>
        </div>
    </section>
</asp:Content>

<asp:Content runat="server" ID="BodyContent" ContentPlaceHolderID="MainContent">
    <div class="left">
        <%if (DBPruebas.CIA == null){%>
        <h3><asp:Label ID="Label8" runat="server" Text="<%$ Resources:InfoNull1 %>"></asp:Label></h3>
        <ol class="round">
            <li class="one">
                <h5><asp:Label ID="Label9" runat="server" Text="<%$ Resources:InfoNull2 %>"></asp:Label></h5>
                <asp:Label ID="Label10" runat="server" Text="<%$ Resources:InfoNull3 %>"></asp:Label>
            </li>
            <li class="two">
                <h5><asp:Label ID="Label11" runat="server" Text="<%$ Resources:InfoNull4 %>"></asp:Label></h5>
                <asp:Label ID="Label12" runat="server" Text="<%$ Resources:InfoNull5 %>"></asp:Label>
            </li>
        </ol>
        <%}else{ %>
        <a href="~/Profile" runat="server"><img src="Images/perfilboton.png" class="coche" /></a>
        <p><asp:Label ID="Label13" runat="server" Text="<%$ Resources:InfoNull6 %>"></asp:Label></p>
        <%} %>
    </div>
    <div class="right">
        <%if (DBPruebas.CIA == null){%>
        <img src="Images/cocheportada.png" class="coche"/>
        <%}else{ %>
        <a id="A1" href="~/Cars" runat="server"><img src="Images/cocheboton.png" class="coche" /></a>
        <p><asp:Label ID="Label14" runat="server" Text="<%$ Resources:InfoNull7 %>"></asp:Label></p>
        <%} %>
    </div>
    <div class="clearfix"></div>
</asp:Content>