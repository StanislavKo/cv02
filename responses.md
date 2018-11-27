# POST /department

POST http://192.168.0.42:8080/department

statusCode:200

{"code":200,"data":{"id":1,"depId":636714,"title":"department636714","users":null}}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":200,"data":{"id":2,"depId":636715,"title":"department636715","users":null}}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":200,"data":{"id":3,"depId":636716,"title":"department636716","users":null}}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":200,"data":{"id":4,"depId":636717,"title":"department636717","users":null}}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":200,"data":{"id":5,"depId":636718,"title":"department636718","users":null}}

# POST /department

POST http://192.168.0.42:8080/department

statusCode:200

{"code":500002,"errorMessage":"DataIntegrityViolationException, could not execute statement; SQL [n/a]; constraint [uk_21ujm2eubctgc9x652n0esnwa]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":500002,"errorMessage":"DataIntegrityViolationException, could not execute statement; SQL [n/a]; constraint [uk_21ujm2eubctgc9x652n0esnwa]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":500002,"errorMessage":"DataIntegrityViolationException, could not execute statement; SQL [n/a]; constraint [uk_21ujm2eubctgc9x652n0esnwa]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":500002,"errorMessage":"DataIntegrityViolationException, could not execute statement; SQL [n/a]; constraint [uk_21ujm2eubctgc9x652n0esnwa]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"}

POST http://192.168.0.42:8080/department

statusCode:200

{"code":500002,"errorMessage":"DataIntegrityViolationException, could not execute statement; SQL [n/a]; constraint [uk_21ujm2eubctgc9x652n0esnwa]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"}

# PUT /department

PUT http://192.168.0.42:8080/department/1

statusCode:200

{"code":200,"data":{"id":1,"depId":636714,"title":"department0Updated","users":[]}}

PUT http://192.168.0.42:8080/department/2

statusCode:200

{"code":200,"data":{"id":2,"depId":636715,"title":"department1Updated","users":[]}}

# POST /user

POST http://192.168.0.42:8080/user

statusCode:200

{"code":200,"data":{"id":1,"firstName":"firstName636714","lastName":"姓名636714","department":{"id":1,"depId":636714,"title":"department0Updated","users":null}}}

POST http://192.168.0.42:8080/user

statusCode:200

{"code":200,"data":{"id":2,"firstName":"firstName636715","lastName":"姓名636715","department":{"id":2,"depId":636715,"title":"department1Updated","users":null}}}

POST http://192.168.0.42:8080/user

statusCode:200

{"code":200,"data":{"id":3,"firstName":"firstName636716","lastName":"姓名636716","department":{"id":3,"depId":636716,"title":"department636716","users":null}}}

POST http://192.168.0.42:8080/user

statusCode:200

{"code":200,"data":{"id":4,"firstName":"firstName636717","lastName":"姓名636717","department":{"id":4,"depId":636717,"title":"department636717","users":null}}}

POST http://192.168.0.42:8080/user

statusCode:200

{"code":200,"data":{"id":5,"firstName":"firstName636718","lastName":"姓名636718","department":{"id":5,"depId":636718,"title":"department636718","users":null}}}

# PUT /user

PUT http://192.168.0.42:8080/user/1

statusCode:200

{"code":200,"data":{"id":1,"firstName":"firstName0Updated","lastName":"姓名0Updated","department":{"id":1,"depId":636714,"title":"department0Updated","users":null}}}

PUT http://192.168.0.42:8080/user/2

statusCode:200

{"code":200,"data":{"id":2,"firstName":"firstName1Updated","lastName":"姓名1Updated","department":{"id":2,"depId":636715,"title":"department1Updated","users":null}}}

# GET /department/{id}

GET http://192.168.0.42:8080/department/1

statusCode:200

{"code":200,"data":"{\"id\":1,\"depId\":636714,\"title\":\"department0Updated\",\"users\":[{\"id\":1,\"firstName\":\"firstName0Updated\",\"lastName\":\"姓名0Updated\"}]}"}

GET http://192.168.0.42:8080/department/2

statusCode:200

{"code":200,"data":"{\"id\":2,\"depId\":636715,\"title\":\"department1Updated\",\"users\":[{\"id\":2,\"firstName\":\"firstName1Updated\",\"lastName\":\"姓名1Updated\"}]}"}

GET http://192.168.0.42:8080/department/3

statusCode:200

{"code":200,"data":"{\"id\":3,\"depId\":636716,\"title\":\"department636716\",\"users\":[{\"id\":3,\"firstName\":\"firstName636716\",\"lastName\":\"姓名636716\"}]}"}

# GET /departments

GET http://192.168.0.42:8080/departments

statusCode:200

{"code":200,"data":[{"id":3,"depId":636716,"title":"department636716","users":null},{"id":4,"depId":636717,"title":"department636717","users":null},{"id":5,"depId":636718,"title":"department636718","users":null},{"id":1,"depId":636714,"title":"department0Updated","users":null},{"id":2,"depId":636715,"title":"department1Updated","users":null}]}

# GET /users

GET http://192.168.0.42:8080/users

statusCode:200

{"code":200,"data":[{"id":3,"firstName":"firstName636716","lastName":"姓名636716","department":{"id":3,"depId":636716,"title":"department636716","users":null}},{"id":4,"firstName":"firstName636717","lastName":"姓名636717","department":{"id":4,"depId":636717,"title":"department636717","users":null}},{"id":5,"firstName":"firstName636718","lastName":"姓名636718","department":{"id":5,"depId":636718,"title":"department636718","users":null}},{"id":1,"firstName":"firstName0Updated","lastName":"姓名0Updated","department":{"id":1,"depId":636714,"title":"department0Updated","users":null}},{"id":2,"firstName":"firstName1Updated","lastName":"姓名1Updated","department":{"id":2,"depId":636715,"title":"department1Updated","users":null}}]}

# GET /users/{depId}/{letters}

GET http://192.168.0.42:8080/users/1/%E5%A7%93%E5%90%8D

statusCode:200

{"code":200,"data":[{"id":1,"firstName":"firstName0Updated","lastName":"姓名0Updated","department":{"id":1,"depId":636714,"title":"department0Updated","users":null}}]}

GET http://192.168.0.42:8080/users/1/asdf

statusCode:200

{"code":200,"data":[]}
