
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <title> ${titulo}</title>

    <link rel="stylesheet" href="../../bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="../../bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="../../bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../../dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect. -->
    <link rel="stylesheet" href="../../dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="../../style/style.css">

</head>

<!-- Para el correcto funcionamiento del script escrito al final debo de especificarlo tanto en el tag body como en le
de subfamilia-->
<!--body onload="filtrarSubFamilias()" background="../../pictures/fondo.png"-->

<!--Cuando presione submit se ejecutara el accion especificado ahi que a su vez me creara un usuario y no hay necesidad
 de agregar los parametros a la url ya que el controlador obtiene los parametros mediante el name especificados en los input-->
<div class="container-form-equipo center">

    <section class="content-header">
        <h1 class="text-center">
            <strong>${agregarequipoi18n}</strong>
        </h1>

        <br>
    </section>

    <form method="post" class="form-horizontal" action="/equipo/crear/" enctype="multipart/form-data">
        <div class="row">

                <div class="form-group">
                    <label for="nombre" class="control-label col-md-3">${nombreequipoi18n}:</label>

                    <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                        <input type="text" name="nombre" class="form-control" required placeholder="Nombre...">
                    </div>

                </div>



                <div class="form-group">
                    <label for="marca" class="control-label col-md-3">${marcaequipoi18n}:</label>

                    <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                        <input type="text" name="marca" class="form-control" placeholder="Marca...">
                    </div>

                </div>


                <div class="form-group">
                    <label for="cantidadExistencia" class="control-label col-md-3">${cantidadequipoi18n}:</label>

                    <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                        <input type="number" name="cantidadExistencia" class="form-control" required placeholder="Cantidad en existencia...">
                    </div>
                </div>




                <div class="form-group">
                    <label for="costoAlquilerPorDia" class="control-label col-md-3">${costoequipoi18n}:</label>

                    <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                        <input type="number" name="costoAlquilerPorDia" class="form-control" required placeholder="Costo del alquiler por dia...">
                    </div>
                </div>



                <div class="form-group">
                    <label for="familia" class="control-label col-md-3">${familiaequipoi18n}:</label>

                    <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                        <select class="form-control" name="familia" id="familia"  onchange="filtrarSubFamilias()">
                            <#list familias as familia>
                                <#if !familia.subFamilia>
                                    <option value="${familia.id}">${familia.nombre}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>

            </div>



            <div class="form-group">
                <label for="subFamilia" class="control-label col-md-3">${subfamiliaequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <select class="form-control" name="subFamilia" id="subFamilia">
                        <#--Esto se autogenerara-->
                    </select>
                </div>


            </div>

            <div class="form-group">
                <label for="file" class="control-label col-md-3">${imagenequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="file" name="files" class="form-control" required placeholder="Foto del equipo...">
                </div>

            </div>


            <div class="form-group">
                <button class="btn btn-primary col-md-offset-5" type="submit">${botonguardari18n}</button>
                <a class="btn btn-danger" href="/equipo/" role="button">${botoncancelari18n}</a>
            </div>


        </div>

    </form>


</div>


</body>

<!--Script para poder seleccionar la respectiva subfamilia de la familia seleccionada -->
<script>
    function filtrarSubFamilias() {
        var subFamilia = [];
        var familiaJS = document.querySelector("#familia").value;

        <#list familias as familia>
        <#if familia.subFamilia>
        var familiaPadreJS = "${familia.familiaPadre.id?string['0']}";

        if (familiaJS == familiaPadreJS) {
            subFamilia.push({ id: "${familia.id}", nombre: "${familia.nombre}" });
        }
        </#if>
        </#list>

        document.querySelector("#subFamilia").innerHTML = "";
        for (var i = 0; i < subFamilia.length; i++) {
            document.querySelector("#subFamilia").innerHTML += '<option value="' + subFamilia[i].id +'">' + subFamilia[i].nombre +'</option>';
        }

        console.table(subFamilia);
    }
</script>
</html>

