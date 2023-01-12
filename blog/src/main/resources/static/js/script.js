

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

