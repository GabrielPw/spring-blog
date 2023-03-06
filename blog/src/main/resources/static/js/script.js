

var mudarVisibilidadeEditor = function() {

    let element = document.getElementById("editor_texto");

    if(element.style.display=='block'){
        element.style.display='none';
    } else {
        element.style.display='block';
    }
};

function editar_postagem(){

    let element = document.getElementById("editor_texto_update");
    let div_postagem = document.getElementById("div_postagem");
    let text_area = document.getElementById("text_area_conteudo");

//    if(element.style.display=='block'){
//            element.style.display='none';
//            div_postagem.display='block'
//    } else {
//        element.style.display='block';
//        div_postagem.display='none';
//    }

    if(div_postagem.style.display == 'none'){
        div_postagem.style.display='block';
        element.style.display='none'
    }else{
        div_postagem.style.display = 'none';
        element.style.display='block'
    }
}

// navbar animation

let mini = true;

function toggleSidebar(){

    if(mini){
        console.log("Mini.");
        document.getElementsByClassName("sidebar")[0].style.width = "250px";
        document.getElementsByClassName("sidebar")[0].style.backgroundColor = "rgb(61,61,61)";

        var spans = document.querySelectorAll('.sidebar span');

        // Itera sobre todas as tags span e define o valor do atributo "display" para "none"
        for (var i = 0; i < spans.length; i++) {
            spans[i].style.display = 'inline';
        }

        mini = false;
    }else{

        console.log("Not mini.");

        document.getElementsByClassName("sidebar")[0].style.width = "80px";
        document.getElementsByClassName("sidebar")[0].style.backgroundColor = "rgb(71,71,71)";

        var spans = document.querySelectorAll('.sidebar span');

        // Itera sobre todas as tags span e define o valor do atributo "display" para "none"
        for (var i = 0; i < spans.length; i++) {
            spans[i].style.display = 'none';
        }

        mini = true;
    }
}
