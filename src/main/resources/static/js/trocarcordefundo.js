var el = document.querySelector("#element")

el.addEventListener("click", changeEl)

function changeEl(){
    if(el.src === "https://img.icons8.com/ios-glyphs/30/white/moon-symbol.png"){
        moon()
    }else{
        sun()
    }
}

function sun(){
    document.querySelector(":root").style.setProperty("--bg" , "white")
    document.querySelector(":root").style.setProperty("--text" , "black")
    document.querySelector(":root").style.setProperty("--gray-50" , "#DCD8D8")
    document.querySelector(":root").style.setProperty("--dark-100" , "#DCD8D8")

    el.src = "https://img.icons8.com/ios-glyphs/30/white/moon-symbol.png"

   // https://img.icons8.com/ios-glyphs/30/000000/moon-symbol.png lua
   // https://img.icons8.com/material-outlined/24/FFFFFF/sun--v1.png sol
   localStorage.setItem("variavel","claro");


}

function moon(){
    document.querySelector(":root").style.setProperty("--bg" , "#303030")
    document.querySelector(":root").style.setProperty("--text" , "white")
    document.querySelector(":root").style.setProperty("--gray-50" , "#302C2C")
    document.querySelector(":root").style.setProperty("--dark-100" , "#302C2C")

    el.src = "https://img.icons8.com/material-outlined/24/FFFFFF/sun--v1.png"
   
    localStorage.setItem("variavel","escuro");


}

(function themeStorage(){

    if(localStorage.getItem("variavel") == "escuro"){
    
        moon()
    
    }else{
    
       sun()
    
       
    
    }
    
    })()


