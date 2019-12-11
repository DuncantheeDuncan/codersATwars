document.addEventListener("DOMContentLoaded", function(){
    const studentsApp = new Vue({
        el : ".app",
        data : {
            firstName : "",
            code_wars_username : "",
            users : []
        },
        methods: {
            addUser : function() {
                const self = this;
                const params = {
                    firstName: self.firstName,
                    code_wars_username: self.code_wars_username
                };
                axios
                    .post("/api/users/add", params)
                    .then(function(result){
                        const data = result.data;
                        self.getAllUsers();
                    });
            },
            getAllUsers : function() {
                const self = this;
                axios
                    .get("/api/users/getUsers")
                    .then(function(result){
                        const data = result.data;
                        console.log(data);
                        self.users = data;
                    });
            },

        }
    });

    studentsApp.getAllUsers();
});