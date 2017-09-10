<%@ Page Title="Cars" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Cars.aspx.cs" Inherits="About" %>
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
    <div class="center clear">
        <h3><asp:Label ID="Label4" runat="server" Text="<%$ Resources:Ticket1 %>"></asp:Label></h3>
        <%if (DBPruebas.getUsuario().getTickets().Count!=0){%>
        <% for(int i=0;i<DBPruebas.getUsuario().getTickets().Count;i++){ %>
        <% UICulture = DBPruebas.getUsuario().getTicket(i).getParquimetro().getLocalidad().getCultura();
                Culture = DBPruebas.getUsuario().getTicket(i).getParquimetro().getLocalidad().getCulture();%>
            <div class="tercio">
                <table>
                    <tr>
                        <th colspan="2"><asp:Label ID="Label3" runat="server" Text="<%$ Resources:Ticket2 %>"></asp:Label> <%: i+1 %></th>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label" runat="server" Text="<%$ Resources:Vehiculo %>"></asp:Label></th>
                        <th><%: DBPruebas.getUsuario().getTicket(i).getVehiculo().getMatricula()%></th>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label2" runat="server" Text="<%$ Resources:Localidad %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getTicket(i).getParquimetro().getLocalidad().getNombre()%></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label1" runat="server" Text="<%$ Resources:Zona %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getTicket(i).getParquimetro().getZona()%></td>
                    </tr>                    
                    <tr>
                        <th><asp:Label ID="Label24" runat="server" Text="<%$ Resources:HoraIn %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getTicket(i).getInicio()%></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label23" runat="server" Text="<%$ Resources:HoraFi %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getTicket(i).getFinal()%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a href="Ticket?state=current&id=<%:i%>"><asp:Label ID="Label22" runat="server" Text="<%$ Resources:Masinfo %>"></asp:Label></a>
                        </td>
                    </tr>
                </table>
            </div>
        <%}
              InitializeCulture();
          }else{ %>
        <div>
            <p><asp:Label ID="Label5" runat="server" Text="<%$ Resources:NoTickets %>"></asp:Label></p>
        </div>
        <%} %>
        
    </div>
    <div class="center clear">
        <h3><asp:Label ID="Label6" runat="server" Text="<%$ Resources:Infracciones %>"></asp:Label></h3>
        <% if(DBPruebas.getUsuario().getInfracciones().Count!=0){ %>
        <% for(int i=0;i<DBPruebas.getUsuario().getInfracciones().Count;i++){ %>
            <div class="tercio">
                <table>
                    <tr>
                        <th colspan="2"><asp:Label ID="Label7" runat="server" Text="<%$ Resources:Infracción %>"></asp:Label> <%: i+1 %></th>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label8" runat="server" Text="<%$ Resources:Localidad %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getInfraccion(i).getLocalidad().getNombre()%></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label9" runat="server" Text="<%$ Resources:Vehiculo %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getVehiculo(DBPruebas.getUsuario().getInfraccion(i).getVehiculo()).getMatricula()%></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label10" runat="server" Text="<%$ Resources:Multa %>"></asp:Label></th>
                        <td><%if (DBPruebas.getUsuario().getInfraccion(i).getLocalidad().getCultura() == "en-GB")
                              { %>£<%:DBPruebas.getUsuario().getInfraccion(i).getMulta() %><%}else{ %><%:DBPruebas.getUsuario().getInfraccion(i).getMulta() %>€<%} %></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label11" runat="server" Text="<%$ Resources:Causa %>"></asp:Label></th>
                        <% switch (DBPruebas.getUsuario().getInfraccion(i).getCausa())
                           {
                               case 0:%>
                                <td><asp:Label ID="Label12" runat="server" Text="<%$ Resources:Causa0 %>"></asp:Label></td>
                            <% break;
                               case 1:%>
                                <td><asp:Label ID="Label13" runat="server" Text="<%$ Resources:Causa1 %>"></asp:Label></td>
                            <% break;
                               case 2:%>
                                <td><asp:Label ID="Label14" runat="server" Text="<%$ Resources:Causa2 %>"></asp:Label></td>
                            <% break;
                           } %>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label15" runat="server" Text="<%$ Resources:Estado %>"></asp:Label></th>
                        <%if(DBPruebas.getUsuario().getInfraccion(i).getImpugnada()){%>
                            <td><asp:Label ID="Label16" runat="server" Text="<%$ Resources:Impugnado %>"></asp:Label></td>
                        <%}else if(DBPruebas.getUsuario().getInfraccion(i).getPagada()){ %>
                            <td><asp:Label ID="Label17" runat="server" Text="<%$ Resources:Pagada %>"></asp:Label></td>
                        <%}else{ %>
                            <td><asp:Label ID="Label18" runat="server" Text="<%$ Resources:Sinpagar %>"></asp:Label></td>
                        <%} %>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a id="A1" href="Ban?id=<%:i%>"><asp:Label ID="Label19" runat="server" Text="<%$ Resources:Masinfo %>"></asp:Label></a>
                        </td>
                    </tr>
                </table>
            </div>
        <%} 
          }else{%>
        <div>
            <p><asp:Label ID="Label20" runat="server" Text="<%$ Resources:Felicidades %>"></asp:Label></p>
        </div>
        <%} %>
    </div>
    <div class="center clear">
        <h3><asp:Label ID="Label21" runat="server" Text="<%$ Resources:Vehiculos %>"></asp:Label></h3>
        <div class="car">
        <% for(int j=0;j<DBPruebas.getUsuario().getVehiculos().Count;j++){ %>
                <a href="Car?car=<%:j%>" class="carA"><%: DBPruebas.getUsuario().getVehiculo(j).getMatricula()%></a>
                <br />
        <%} %>
        </div>
    </div>
</asp:Content>