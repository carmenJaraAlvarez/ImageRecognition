var feedback = function(res) {
    if (res.success === true) {
        var get_link = res.data.link.replace(/^http:\/\//i, 'https://');
        document.querySelector('.status').classList.add('bg-success');
        document.querySelector('.status').innerHTML =
            'Image : ' + '<br><input class="image-url" value=\"' + get_link + '\"/>' + '<img class="img" alt="Imgur-Upload" src=\"' + get_link + '\"/>';
    }
};

new Imgur({
    clientid: '4409588f10776f7', //
    callback: feedback
});
//Nuestro mail: tecnogad.aiss.g4@gmail.com
//Clave: juacarpepraf
//https://www.youtube.com/watch?v=qmNbZmBOaGM
//Nuestro mail: tecnogad.aiss.g4@gmail.com
//https://api.imgur.com/oauth2/addclient
//clien secret e9a5a17599a41ef7d5f71b2ae22f575615127951
//juacarpepraf10 imgur
//http://localhost:8090/oauth2callback/Imgur