var app=new Vue({
    el:"#app",
    data:{
        // 专业
        showDepartment:false,
        showDeptUpdate:false,
        department:[],
        staffNum:"",
        newDeptName:"",
        updateDeptName:[],
        // 专业

        //学生
        showStudent:false,
        showStudentUpdate:false,
        student:[],
        newStudentName:"",
        newStudentDept:"",
        updateStudentName:[],
        //学生

        //课程
        showClass:false,
        showClassUpdate:false,
        showClassGrade:false,
        gradeClassName:"",
        lesson:[],
        classGrade:[],
        newClassName:"",
        updateClassName:[],
        //课程

        //成绩
        showGrade:false,
        showGradeUpdate:false,
        showAvgGrade:false,
        grade:[],
        newGradeName:"",
        updateGradeName:[],
        StudentId:"",

        avgClassName:"",
        avgGradeByDept:[]
        //成绩

    },
    methods: {
        //专业
        listDept: function () {
            var that = this;
            this.showStudent = false;
            this.showClassGrade = false;
            this.showStudentUpdate = false;
            this.showClass = false;
            this.showClassUpdate = false;
            this.showGrade = false;
            this.showGradeUpdate = false;
            this.showAvgGrade = false;

            this.showDepartment = true;
            axios.get("http://localhost:8090/dbexp/dc/list").then(
                function (response) {
                    that.department = response.data.data
                }
            )
        },


        showTotalNum: function (departmentName) {
            var that = this;
            this.showDepartment = true;
            axios.get("http://localhost:8090/dbexp/dc/totalnum?department_name=" + departmentName).then(
                function (response) {
                    if (response.data.data != "") {
                        alert("该专业总人数为" + response.data.data[0].totalNum + "人")
                    } else {
                        alert("该专业总人数为0人")
                    }
                },
            )
        },

        insertDept: function () {
            var that = this;
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/dc/insert",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    department_name: that.newDeptName
                }
            }).then(function (response) {
                if (response.data.msg === "already_exist") {
                    alert("该项目已存在")
                } else {
                    alert("添加成功,请刷新")
                }
            })
        },

        deleteDept: function (index) {
            var that = this;
            console.log(that.department[index].departmentId)
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/dc/delete",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    department_id: that.department[index].departmentId
                }
            }).then(function (response) {
                alert("删除成功,请刷新")
            })
        },

        changeUpdateState: function () {
            this.showDeptUpdate = !this.showDeptUpdate
        },


        updateDept: function (index) {
            var that = this;
            console.log(that.department[index].departmentId);
            console.log(that.updateDeptName)

            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/dc/update",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    department_id: that.department[index].departmentId,
                    department_name: that.updateDeptName[index]
                }
            }).then(function (response) {
                alert("修改成功,请刷新")
            })
        },
        //专业

        // 学生
        listStudent: function () {
            var that = this;
            this.showDepartment = false;
            this.showDeptUpdate = false;
            this.showClassGrade = false;
            this.showClass = false;
            this.showClassUpdate = false;
            this.showGrade = false;
            this.showGradeUpdate = false;
            this.showAvgGrade = false;

            this.showStudent = true;

            axios.get("http://localhost:8090/dbexp/sc/list").then(
                function (response) {
                    that.student = response.data.data
                }
            )
            console.log(that.student)
        },

        insertStudent: function () {
            var that = this;
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/sc/insert",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    sname: that.newStudentName,
                    dname: that.newStudentDept
                }
            }).then(function (response) {
                if (response.data.msg === "dept_not_exist") {
                    alert("专业不存在")
                } else {
                    alert("添加成功,请刷新")
                }
            })
        },

        deleteStudent: function (index) {
            var that = this;
            console.log(that.student[index].studentId)
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/sc/delete",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    sid: that.student[index].studentId
                }
            }).then(function (response) {
                alert("删除成功,请刷新")
            })
        },

        changeStudentUpdateState: function () {
            this.showStudentUpdate = !this.showStudentUpdate
        },


        updateStudent: function (index) {
            var that = this;

            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/sc/update",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    sid: that.student[index].studentId,
                    sname: that.updateStudentName[index]
                }
            }).then(function (response) {
                alert("修改成功,请刷新")
            })
        },
        // 学生

        // 课程
        listClass: function () {
            var that = this;
            this.showClass = true;

            this.showDepartment = false;
            this.showDeptUpdate = false;
            this.showStudent = false;
            this.showStudentUpdate = false;
            this.showClassGrade = false;
            this.showGrade = false;
            this.showGradeUpdate = false;
            this.showAvgGrade = false;

            axios.get("http://localhost:8090/dbexp/cic/list").then(
                function (response) {
                    that.lesson = response.data.data
                }
            )
        },

        getclassgrade: function (cname) {
            var that = this;
            this.showClass = true;

            this.showDepartment = false;
            this.showDeptUpdate = false;
            this.showStudent = false;
            this.showStudentUpdate = false;

            axios.get("http://localhost:8090/dbexp/cic/getclassgrade?cname=" + cname).then(
                function (response) {
                    that.gradeClassName = cname;
                    that.classGrade = response.data.data;
                    console.log(that.classGrade)
                }
            )
        },

        insertClass: function () {
            var that = this;
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/cic/insert",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    class_name: that.newClassName
                }
            }).then(function (response) {
                if (response.data.msg === "already_exist") {
                    alert("该项目已存在")
                } else {
                    alert("添加成功,请刷新")
                }
            })
        },

        deleteClass: function (index) {
            var that = this;
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/cic/delete",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    class_id: that.lesson[index].classId
                }
            }).then(function (response) {
                alert("删除成功,请刷新")
            })
        },

        changeClassUpdateState: function () {
            this.showClassUpdate = !this.showClassUpdate
        },

        changeClassGradeState: function () {
            this.showClassGrade = !this.showClassGrade
        },


        updateClass: function (index) {
            var that = this;

            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/cic/update",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    class_id: that.lesson[index].classId,
                    class_name: that.updateClassName[index]
                }
            }).then(function (response) {
                alert("修改成功,请刷新")
            })
        },


        // 课程

        //成绩
        listGrade: function () {
            var that = this;
            this.showDepartment = false;
            this.showDeptUpdate = false;
            this.showClassGrade = false;
            this.showClass = false;
            this.showClassUpdate = false;
            this.showGradeUpdate = false;
            this.showAvgGrade = false;
            this.showStudent = false;

            this.showGrade = true;

            axios.get("http://localhost:8090/dbexp/gc/list").then(
                function (response) {
                    that.grade = response.data.data
                }
            )
        },

        listAvgGrade: function () {
            var that = this;
            this.showDepartment = false;
            this.showDeptUpdate = false;
            this.showClassGrade = false;
            this.showClass = false;
            this.showClassUpdate = false;
            this.showGradeUpdate = false;
            this.showStudent = false;

            this.showGrade = true;
            this.showAvgGrade = true;

            axios.get("http://localhost:8090/dbexp/gc/getavg?cname="+that.avgClassName).then(
                function (response) {
                    that.avgGradeByDept = response.data.data
                }
            )
        },

        insertGrade: function () {
            var that = this;
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/gc/insert",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    cname: that.newClassName,
                    cgrade: that.newGradeName,
                    sid: that.StudentId
                }
            }).then(function (response) {
                if (response.data.msg === "class_not_exist") {
                    alert("课程不存在")
                } else if (response.data.msg === "student_not_exist") {
                    alert("学生不存在")
                } else {
                    alert("添加成功,请刷新")
                }
            })
        },

        deleteGrade: function (index) {
            var that = this;
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/gc/delete",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    gid: that.grade[index].gid
                }
            }).then(function (response) {
                alert("删除成功,请刷新")
            })
        },

        changeGradeUpdateState: function () {
            this.showGradeUpdate = !this.showGradeUpdate
        },


        updateGrade: function (index) {
            var that = this;

            console.log(that.grade[index].gid+":"+ that.updateGradeName[index]);
            axios({
                method: "post",
                changeOrigin: "true",
                url: "http://localhost:8090/dbexp/gc/update",
                transformRequest: [
                    function (data) {
                        // 对 data 进行任意转换处理
                        return Qs.stringify(data);
                    }
                ],
                data: {
                    gid: that.grade[index].gid,
                    cgrade: that.updateGradeName[index]
                }
            }).then(function (response) {
                alert("修改成功,请刷新")
            })
        }

        //成绩
    },
})