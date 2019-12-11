document.addEventListener("DOMContentLoaded", function() {

    const codersAtWar = new Vue({

        "el": ".app",

        data: {
            codewarslist : []
        },
        methods: {
            getUsers: function(){

                let self = this;

                axios
                .get("/api/users/getUsers")
                .then(function(res){

                    console.log(res.data);
                    
                })
            }
        },

    })
    codersAtWar.getUsers();
})

