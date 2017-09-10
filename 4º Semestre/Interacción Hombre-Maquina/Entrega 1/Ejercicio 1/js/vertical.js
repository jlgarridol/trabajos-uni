function vertical() {
    var informacion = document.getElementsByClassName("informacion");
    for(var i = 0; i<informacion.length;i++){
        if(informacion[i].style.float=='none'){
            informacion[i].style.float='left';
            informacion[i].style.margin="40px 2.5%";
            document.getElementById("simple").style.display='block';
            document.getElementById("doble").style.display='none';
        }else{
            informacion[i].style.float='none';
            informacion[i].style.margin="40px auto";
            document.getElementById("doble").style.display='block';
            document.getElementById("simple").style.display='none';
        }
    }
}