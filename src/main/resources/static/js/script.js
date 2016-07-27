function handleSelect() {
    var myList = document.getElementById("selectAulario");
    var aulario = myList.options[myList.selectedIndex].value;
    console.log("Aulario: ", aulario);
    var search = {
        "aulario": aulario
    };
    console.log("Search: ", search);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/ajax/aulas",
        data: JSON.stringify(search),
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            var sel = document.getElementById("selectAula");
            removeOptions(sel);
            fillAulas(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
            display(e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });

}


function display(data) {
    var json = JSON.stringify(data, null, 4);
    $('#feedback').html(json);
}

function removeOptions(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

function fillAulas(data) {
    
    
    var aulas = data.aulas;
    console.log("Aulas: ", aulas);
    var sel = document.getElementById('selectAula');
    for (var i = 0; i < aulas.length; i++) {
        var opt = document.createElement('option');
        opt.innerHTML = aulas[i];
        opt.value = aulas[i];
        sel.appendChild(opt);
    }
    document.getElementById("selectAula").disabled = false;
}
