<%@ Page Title="Ticket" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeFile="Ticket.aspx.cs" Inherits="About" %>
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
    <div class="hidden">
        <p id="correcto1"> <asp:Label ID="Label12" runat="server" Text="<%$ Resources:Correcto1 %>"></asp:Label></p>
        <p id="correcto2"> <asp:Label ID="Label13" runat="server" Text="<%$ Resources:Correcto2 %>"></asp:Label></p>
        <p id="MenorOIgual"><asp:Label ID="Label14" runat="server" Text="<%$ Resources:MenorOIgual %>"></asp:Label></p>
        <p id="Confirmar"><asp:Label ID="Label15" runat="server" Text="<%$ Resources:Confirmar %>"></asp:Label></p>
        <p id="AmpliarTiempo"><asp:Label ID="Label16" runat="server" Text="<%$ Resources:Ampliartiempo %>"></asp:Label></p>
    </div>

    <% bool state=false;
       if (Request.QueryString["state"] == "current") state = true;
       int indice = Int32.Parse(Request.QueryString["id"]);
       Ticket ticket;
       if (state)
           ticket = DBPruebas.getUsuario().getTicket(indice);
       else
           ticket = DBPruebas.getUsuario().getVehiculo(Int32.Parse(Request.QueryString["car"])).getTicket(indice);

       if (Request.Form["increase"] != null)
       {
           DBPruebas.AlterParkingTime(indice, Int32.Parse(Request.Form["increase"]));
       } %>
    <%
        TimeSpan resta;
        if (state) {
            DateTime total = ticket.getInicio().AddHours(2);
            DateTime final = ticket.getFinal();
            resta = total - final;
            this.minutos = (int) resta.TotalMinutes;
            if (ticket.getParquimetro().getLocalidad().getCultura() == "en-GB")
                this.abono = "" + (minutos * 0.01);
            else
                this.abono = "" + (minutos * 0.011);
            this.abono = this.abono.Replace(',', '.');
        }
         %>
    <h1> <asp:Label ID="Label9" runat="server" Text="<%$ Resources:Ticket %>"></asp:Label> <% if(state){ %> <asp:Label ID="Label10" runat="server" Text="<%$ Resources:actual %>"></asp:Label> <%}else{ %> <asp:Label ID="Label11" runat="server" Text="<%$ Resources:vencido %>"></asp:Label> <%} %> <%: indice+1 %></h1>
    <div class="cuarto"><p> <asp:Label ID="Label5" runat="server" Text="<%$ Resources:Vehiculo %>"></asp:Label> </p></div><div class="trescuarto"><p><%:ticket.getVehiculo().getMatricula() %></p></div>
    <div class="cuarto"><p> <asp:Label ID="Label1" runat="server" Text="<%$ Resources:Localidad %>"></asp:Label> </p></div><div class="trescuarto"><p><%:ticket.getParquimetro().getLocalidad().getNombre() %></p></div>
    <div class="cuarto"><p> <asp:Label ID="Label2" runat="server" Text="<%$ Resources:Zona %>"></asp:Label> </p></div><div class="trescuarto"><p><%:ticket.getParquimetro().getZona() %></p></div>
    <div class="cuarto"><p> <asp:Label ID="Label3" runat="server" Text="<%$ Resources:Abonado %>"></asp:Label> </p></div><div class="trescuarto"><p><%if(ticket.getParquimetro().getLocalidad().getCultura()=="en-GB"){ %>£<%:ticket.getAbonado() %><%}else{ %><%:ticket.getAbonado() %>€<%} %></p></div>
    <div class="cuarto"><p> <asp:Label ID="Label4" runat="server" Text="<%$ Resources:Horaini %>"></asp:Label> </p></div><div class="trescuarto"><p><%:ticket.getInicio() %></p></div>
    <div class="cuarto"><p> <asp:Label ID="Label6" runat="server" Text="<%$ Resources:Horafi %>"></asp:Label> </p></div><div class="trescuarto"><p><%:ticket.getFinal() %></p></div>
    <%if (state){ %>
    <div class="cuarto">
        <ul id="desaparecer">
            <li style="list-style: none"><input class="radio" type="radio" name="action" checked="checked" id="tiempo"/>  <asp:Label ID="Label7" runat="server" Text="<%$ Resources:Aplazartiempo %>"></asp:Label></li>
            <li style="list-style: none"><input class="radio" type="radio" name="action" id="dinero" /> <asp:Label ID="Label8" runat="server" Text="<%$ Resources:Aplazardinero %>"></asp:Label></li>
        </ul>
        <input type="button" id="button1" onclick="mostrar()" />
        <div class="hidden" id="supp1">
        <asp:TextBox runat="server" TextMode="Number" ID="minuto"></asp:TextBox>
        </div>
        <div class="hidden" id="supp2">
            <%if(ticket.getParquimetro().getLocalidad().getCultura() == "en-GB"){ %>£<asp:TextBox runat="server" CssClass="casi100"></asp:TextBox><%}else{ %>
            <asp:TextBox runat="server"  CssClass="casi100"></asp:TextBox>€
            <%} %>
        
        </div>
        <input type="button" id="button2" class="hidden" onclick="cuasiconfirmacion()" />
    </div>
    <script type="text/javascript">
        document.getElementById("button1").value = document.getElementById("AmpliarTiempo").firstChild.innerHTML;
        document.getElementById("button2").value = document.getElementById("Confirmar").firstChild.innerHTML;        
        function mostrar() {
            document.getElementById("desaparecer").className = "hidden";
            document.getElementById("button1").className = "hidden";
            document.getElementById("button2").className = "";
            if (document.getElementById("tiempo").checked)
                document.getElementById("supp1").className = "";
            else
                document.getElementById("supp2").className = "";
        }
        function cuasiconfirmacion() {
            var tiempo;
            var dinero;
            var correcto = false;
            if (document.getElementById("tiempo").checked) {
                if (document.getElementById("MainContent_minuto").value > <%:this.minutos%>.0 || document.getElementById("MainContent_minuto").value <= 0)
                    alert(document.getElementById("MenorOIgual").firstChild.innerHTML+" <%: minutos%>");
                else {
                    tiempo = parseInt(document.getElementById("MainContent_minuto").value)
                    <%if(ticket.getParquimetro().getLocalidad().getCultura() == "en-GB"){%>
                    dinero = tiempo * 0.01;
                    <%}else{%>
                    dinero = tiempo * 0.011;
                    <%}%>
                    correcto = true;
                }
                
            } else {
                if (parseFloat(document.getElementsByClassName("casi100")[0].value) > parseFloat("<%:this.abono%>") || parseFloat(document.getElementsByClassName("casi100")[0].value) <= 0) {
                    alert(document.getElementById("MenorOIgual").firtChild.innerHTML+" <%if(ticket.getParquimetro().getLocalidad().getCultura() == "en-GB"){%>£<%: abono%><%}else{%><%: abono%>€<%} %>");
                } else {
                    dinero = parseFloat(document.getElementsByClassName("casi100")[0].value)
                    <%if(ticket.getParquimetro().getLocalidad().getCultura() == "en-GB"){%>
                    tiempo = parseInt(dinero / 0.01);
                    <%}else{%>
                    tiempo = parseInt(dinero / 0.011);
                    <%}%>
                    correcto = true;
                }
            }
            if (correcto) {
                var correcto = "" + "<%: this.Label12.Text%>" + "";
                var masCorrecto = "" + "<%: this.Label13.Text%>" + "";
                if (confirm( correcto +" " + tiempo +" "+ masCorrecto + " " <%if(ticket.getParquimetro().getLocalidad().getCultura() == "en-GB"){%> + "£" + dinero<%}else{%> + dinero + "€"<%} %>)) {
                    conexion = new XMLHttpRequest()
                    conexion.open('POST', window.location);
                    conexion.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    conexion.send("increase=" + tiempo);
                    document.forms[0].submit();
                }
            }
       }
    </script> 
    <%} %>

</asp:Content>