document.addEventListener("DOMContentLoaded", function() {

    const codersAtWar = new Vue({
        el: ".app",
        data: {
            users : [],
            i : 0,
            selectedUser : {}
        },
        methods: {
            getUsers: function(){
                const me = this;

                this.users = [];
                axios
                    .get("/api/users/getUsers")
                    .then(function(coderResults){

                        const results = coderResults.data.map(function(user) {
                            user.url = `https://www.codewars.com/api/v1/users/${user.username}`;
                            return user;
                        });
                        const realData = results;
                         me.users = realData;

                       })
            },
//---
processForm: function(e){

e.preventDefault();

let self = this;

axios
.post("/api/users/add",{fullname:self.fullname, codewarsusername: self.codewarsusername})
console.log("name " + fullname )
.then(function (res){})
.catch(function (e){ console.log(e);})

self.clear();
console.log("woooooooooooooooooooooooooooooooooooo");
},
//---
            getUserData : function(username) {
                const self = this;
                axios
                    .get(`https://codewars-proxy.herokuapp.com/api/user/${username}`)
                    .then(function(result){
                        //console.log(result.data);
                        console.log("Name= " + result.data.name +
                         "\n" + "Username = " +
                          result.data.username +
                           "\n" + "Honor= "
                           +result.data.honor);

                        self.selectedUser = result.data;
                    });

            },





        },
        mounted : function() {
            this.getUsers();
        }
    });

//    codersAtWar.getUsers();// commented


//    setInterval(function(){
        codersAtWar.addUser();
//        console.log("...");
//    }, 1000);
})

