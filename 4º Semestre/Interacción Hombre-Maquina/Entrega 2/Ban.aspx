<%@ Page Title="Baned" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Ban.aspx.cs" Inherits="About" %>
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
    <div class="hidden">
        <p id="confirm1"><asp:Label runat="server" Text="<%$ Resources:ConfirmPay %>"></asp:Label></p>
        <p id="confirm2"><asp:Label runat="server" Text="<%$ Resources:ContestThePayload %>"></asp:Label></p>
    </div>
    <% if (Request.Form["ctl00$MainContent$pagar"] != null)
       {
           %>
        <script>

            if (confirm(document.getElementById("confirm1").firstChild.innerHTML)) {
                conexion = new XMLHttpRequest()
                conexion.open('POST', window.location);
                conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                conexion.send("realPay=''");
                document.forms[0].submit();
            }
            </script>
    <%
       }
       else if (Request.Form["ctl00$MainContent$impugnar"] != null)
       {%>
        <script>
            if (confirm(document.getElementById("confirm2").firstChild.innerHTML)) {
                conexion = new XMLHttpRequest()
                conexion.open('POST', window.location);
                conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                conexion.send("realContest=''");
                document.forms[0].submit();
            }
            </script>
    <%
       }
       else if (Request.Form["realPay"] != null)
       {
           DBPruebas.PayTresspassing(Int32.Parse(Request.QueryString["id"]));
       }
       else if (Request.Form["realContest"] != null)
       {
           DBPruebas.RequestTresspassing(Int32.Parse(Request.QueryString["id"]));
       }
       
       %>
    <% Infraccion infraccion = DBPruebas.getUsuario().getInfraccion(Int32.Parse(Request.QueryString["id"])); %>
    <h1><asp:Label ID="Label2" runat="server" Text="<%$ Resources:Infraccion %>"></asp:Label><%: Int32.Parse(Request.QueryString["id"])+1 %></h1>
    <div class="cuarto"><p><asp:Label runat="server" Text="<%$ Resources:Vehiculo %>"></asp:Label> </p></div><div class="trescuarto"><p><%:DBPruebas.getUsuario().getVehiculo(infraccion.getVehiculo()).getMatricula() %></p></div>
    <div class="cuarto"><p><asp:Label ID="Label1" runat="server" Text="<%$ Resources:Localidad %>"></asp:Label> </p></div><div class="trescuarto"><p><%:infraccion.getLocalidad().getNombre() %></p></div>
    <div class="cuarto"><p>Causa: </p></div><div class="trescuarto"><p>
                        <% switch (infraccion.getCausa())
                           {
                               case 0:%>
                                <asp:Label ID="Label3" runat="server" Text="<%$ Resources:Causa0 %>"></asp:Label>
                            <% break;
                               case 1:%>
                                <asp:Label ID="Label4" runat="server" Text="<%$ Resources:Causa1 %>"></asp:Label>
                            <% break;
                               case 2:%>
                                <asp:Label ID="Label5" runat="server" Text="<%$ Resources:Causa2 %>"></asp:Label>
                            <% break;
                           } %>
    </p></div>
    <div class="cuarto"><p><asp:Label ID="Label6" runat="server" Text="<%$ Resources:Multa %>"></asp:Label> </p></div><div class="trescuarto"><p><%if(infraccion.getLocalidad().getCultura()=="en-GB"){ %>£<%:infraccion.getMulta() %><%}else{ %><%:infraccion.getMulta() %>€<%} %></p></div>
    <div class="cuarto"><p><%if(infraccion.getImpugnada()){ %> <asp:Label ID="Label7" runat="server" Text="<%$ Resources:Impugnada %>"></asp:Label><%}else if(infraccion.getPagada()){ %><asp:Button Text="<%$ Resources:Impugnar %>" runat="server" ID="impugnar"/><%}else{%><asp:Button Text="<%$ Resources:Pagar %>" runat="server" ID="pagar"/><%}%></p></div>

</asp:Content>