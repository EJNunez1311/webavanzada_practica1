
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
<!--body background="../../pictures/fondo.png"-->

<div class="container-form-equipo center">

    <section class="content-header">
        <h1 class="text-center">
            <strong>${editarequipoi18n}</strong>
        </h1>

        <br>
    </section>

    <form method="post" class="form-horizontal" action="/equipo/editar/?id=${equipo.id}" enctype="multipart/form-data">
        <div class="row">

            <div class="form-group">
                <label for="nombre" class="control-label col-md-3">${nombreequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="nombre" value="${equipo.nombre}" required class="form-control" placeholder="Nombre...">
                </div>

            </div>



            <div class="form-group">
                <label for="marca" class="control-label col-md-3">${marcaequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="text" name="marca" value="${equipo.marca}" class="form-control" placeholder="Marca...">
                </div>

            </div>


            <div class="form-group">
                <label for="cantidadExistencia" class="control-label col-md-3">${cantidadequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="number" name="cantidadExistencia"  value="${equipo.cantidadExistencia}" required class="form-control" placeholder="Cantidad en existencia...">
                </div>
            </div>




            <div class="form-group">
                <label for="costoAlquilerPorDia" class="control-label col-md-3">${costoequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="number" name="costoAlquilerPorDia" value="${equipo.costoAlquilerPorDia}" required class="form-control" placeholder="Costo del alquiler por dia...">
                </div>
            </div>



            <!--De esta forma manejo la seleccion de la familia elegida para conseguirla de nuevo aqui en el edit
             y mediante este codigo me aseguro que se elija la correcta-->
            <div class="form-group">
                <label for="familia" class="control-label col-md-3">${familiaequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <select class="form-control" name="familia">
                        <#list familias as familia>
                            <#if equipo.familia.id == familia.id>
                                <option value="${equipo.familia.id}" >${equipo.familia.nombre}</option>
                            <#else>
                                <option value="${familia.id}">${familia.nombre}</option>
                            </#if>
                        </#list>
                    </select>
                </div>

            </div>



            <div class="form-group">
                <label for="subFamilia" class="control-label col-md-3">${subfamiliaequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <select class="form-control" name="subFamilia">
                        <#list familias as familia>
                            <#if familia.subFamilia>
                                <#if equipo.subFamilia.id == familia.id>
                                    <option value="${equipo.familia.id}" >${equipo.familia.nombre}</option>
                                <#else>
                                    <option value="${familia.id}">${familia.nombre}</option>
                                </#if>
                            </#if>
                        </#list>
                    </select>
                </div>


            </div>

            <div class="form-group">
                <label for="file" class="control-label col-md-3">${imagenequipoi18n}:</label>

                <div class="col-sm-6 col-md-6 col-lg-6 col-xs-6">
                    <input type="file" name="files" value="${equipo.imagenEquipo}" required class="form-control" placeholder="Foto del equipo...">
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

</html>

