$(function () {
    if ($.trim($(".msg_error").text()) !== "") {
        $(".msg_error").css("visibility", "visible");
    }
});

function tutorialesIns() {
    window.location = "faces/tutorialesIns.xhtml";
}

function tutorialesDel() {
    var ids = [];

    $("input[name='idtutorial_del']:checked").each(function () {
        ids.push($(this).val());
    });

    if (ids.length === 0) {
        message("Advertencia", "Seleccione fila(s) a Retirar");
    } else {
        $("#message").html("¿Retirar registro(s)?");
        $("#dlg_message").dialog({
            modal: true,
            title: "Advertencia",
            width: 340,
            buttons: {
                "No": function () {
                    $(this).dialog("close");
                },
                "Si": function () {
                    $(this).dialog("close");
                    $("#form_del\\:ids").val(ids.toString());
                    $("#form_del\\:tutorialesDel").click();
                }
            }
        });
    }
}

function tutorialesUpd() {
    var id = $("input[name='idtutorial_upd']:checked").val();

    if (isNaN(id)) {
        message("Advertencia", "Seleccione Fila para Actualizar Datos");
    } else {
        $("#form_get\\:idtutorial").val(id);
        $("#form_get\\:tutorialesGet").click();
    }
}


