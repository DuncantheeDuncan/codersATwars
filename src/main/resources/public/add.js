document.addEventListener("DOMContentLoaded", function () {


    const addUsers = new Vue({
        el: ".app",

        data: {
            fullname: "",
            codewarsusername: "",
        },

        methods: {
            addCustomer(e) {
                //Define a variable to hold this
                let self = this;
                // check if all fields are filled
                if (
                    !self.fullname ||
                    !self.codewarsusername

                ) {
                    console.log("Please fill all fields");
                } else {
                    let customers = {
                        fullname: self.fullname,
                        codewarsusername: self.codewarsusername,

                    };

                    axios()
                        .post('/api/users/add', customers)
                        .then(response => {
                            console.log(response);
                            if (response.status == 200) this.$router.push({ path: "/" });
                        });
                }
                e.preventDefault();
            }
        },

    })
})