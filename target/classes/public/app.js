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
                    .then(function(coderResults){
//                        console.log(coderResults.data);
                        console.log(JSON.parse(coderResults.data[0]))
                        coderResults.forEach(function (results) {
                        console.log(results.data)
                    })
                })
            }
        }
    })
    codersAtWar.getUsers();
})

