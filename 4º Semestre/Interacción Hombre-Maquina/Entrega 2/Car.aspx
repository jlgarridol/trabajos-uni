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
    <% int indiceCoche = Int32.Parse(Request.QueryString["car"]); %>
    <div class="center clear">
        <h1><asp:Label ID="Label6" runat="server" Text="<%$ Resources:Vehiculo1 %>"></asp:Label> <%: DBPruebas.getUsuario().getVehiculo(indiceCoche).getMatricula() %></h1>
        <h3><asp:Label ID="Label4" runat="server" Text="<%$ Resources:Ticket1 %>"></asp:Label></h3>
        <%if (DBPruebas.getUsuario().getVehiculo(indiceCoche).getTickets().Count!=0){%>
        <% for(int i=0;i<DBPruebas.getUsuario().getVehiculo(indiceCoche).getTickets().Count;i++){ %>
            <div class="tercio">
                <table>
                    <tr>
                        <th colspan="2"><asp:Label ID="Label3" runat="server" Text="<%$ Resources:Ticket2 %>"></asp:Label> <%: i+1 %></th>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label2" runat="server" Text="<%$ Resources:Localidad %> " ></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getVehiculo(indiceCoche).getTicket(i).getParquimetro().getLocalidad().getNombre()%></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label1" runat="server" Text="<%$ Resources:Zona %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getVehiculo(indiceCoche).getTicket(i).getParquimetro().getZona()%></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label" runat="server" Text="<%$ Resources:Importe %>"></asp:Label></th>
                        <td><%if(DBPruebas.getUsuario().getVehiculo(indiceCoche).getTicket(i).getParquimetro().getLocalidad().getCultura() == "en-GB"){%> £ <%: DBPruebas.getUsuario().getVehiculo(indiceCoche).getTicket(i).getAbonado()%><%}else{%> <%: DBPruebas.getUsuario().getVehiculo(indiceCoche).getTicket(i).getAbonado()%> €<%} %></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label24" runat="server" Text="<%$ Resources:HoraIn %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getVehiculo(indiceCoche).getTicket(i).getInicio()%></td>
                    </tr>
                    <tr>
                        <th><asp:Label ID="Label23" runat="server" Text="<%$ Resources:HoraFi %>"></asp:Label></th>
                        <td><%: DBPruebas.getUsuario().getVehiculo(indiceCoche).getTicket(i).getFinal()%></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a href="Ticket?state=past&id=<%:i%>&car=<%:indiceCoche%>"><asp:Label ID="Label22" runat="server" Text="<%$ Resources:Masinfo %>"></asp:Label></a>
                        </td>
                    </tr>
                </table>
            </div>
        <%
        }
          }else{ %>
        <div>
            <p><asp:Label ID="Label5" runat="server" Text="<%$ Resources:NoTickets %>"></asp:Label></p>
        </div>
        <%} %>
    </div>
</asp:Content>