# opwire-agent: sample command line in Java

## Install

Clone example source code from `github`:

```shell
git clone https://github.com/opwire/sample-cmdline-java.git
```

Change the project home to current working directory:

```shell
cd sample-cmdline-java
```

Install dependencies:

```shell
mvn clean package
```

Download and extract the latest [`opwire-agent`](https://github.com/opwire/opwire-agent/releases/latest) program into this directory:

![project-home-dir](https://raw.github.com/opwire/sample-cmdline-java/master/docs/assets/images/ls.png)

## Test the service using a web browser

Execute the following command:

```shell
./opwire-agent -p=8888 --default-command="java -jar target/sample-cmdline-java-1.0.0-jar-with-dependencies.jar"
```

Open this URL `http://localhost:8888/run?type=microservice&type=java`:

![example-output](https://raw.github.com/opwire/sample-cmdline-java/master/docs/assets/images/example.png)


## Test the service using `curl`

### Default data format (`json`)

Execute the following command:

```shell
./opwire-agent -p=8888 --default-command="java -jar target/sample-cmdline-java-1.0.0-jar-with-dependencies.jar"
```

#### Valid input (a JSON object)

Make a HTTP request with `curl`:

```curl
curl -v \
  --request POST \
  --url 'http://localhost:8888/run?type=microservice&type=java' \
  --data '{
  "name": "Opwire",
	"url": "https://opwire.org/"
}'
```

Result:

```plain
> POST /run?type=microservice&type=java HTTP/1.1
> Host: localhost:8888
> User-Agent: curl/7.54.0
> Accept: */*
> Content-Length: 52
> Content-Type: application/x-www-form-urlencoded
> 
* upload completely sent off: 52 out of 52 bytes
< HTTP/1.1 200 OK
< Content-Type: text/plain
< X-Exec-Duration: 0.495879
< Date: Fri, 15 Mar 2019 04:13:14 GMT
< Content-Length: 430
< 
{
  "input": {
    "name": "Opwire",
    "url": "https://opwire.org/"
  },
  "OPWIRE_REQUEST": {
    "header": {
      "Accept": [
        "*/*"
      ],
      "Content-Length": [
        "52"
      ],
      "Content-Type": [
        "application/x-www-form-urlencoded"
      ],
      "User-Agent": [
        "curl/7.54.0"
      ]
    },
    "query": {
      "type": [
        "microservice",
        "java"
      ]
    }
  }
}
```

#### Invalid input (not a JSON object)

Make a HTTP request with `curl`:

```curl
curl -v \
  --request POST \
  --url 'http://localhost:8888/run?type=microservice&type=java' \
  --data 'Not a JSON object'
```

Result:

```plain
> POST /run?type=microservice&type=java HTTP/1.1
> Host: localhost:8888
> User-Agent: curl/7.54.0
> Accept: */*
> Content-Length: 17
> Content-Type: application/x-www-form-urlencoded
> 
* upload completely sent off: 17 out of 17 bytes
< HTTP/1.1 500 Internal Server Error
< Content-Type: text/plain
< X-Error-Message: exit status 1
< Date: Fri, 15 Mar 2019 04:16:08 GMT
< Content-Length: 1032
< 
{
  "stack": [
    {
      "declaringClass": "com.google.gson.Gson",
      "methodName": "fromJson",
      "fileName": "Gson.java",
      "lineNumber": 939
    },
    {
      "declaringClass": "com.google.gson.Gson",
      "methodName": "fromJson",
      "fileName": "Gson.java",
      "lineNumber": 892
    },
    {
      "declaringClass": "com.google.gson.Gson",
      "methodName": "fromJson",
      "fileName": "Gson.java",
      "lineNumber": 841
    },
    {
      "declaringClass": "com.google.gson.Gson",
      "methodName": "fromJson",
      "fileName": "Gson.java",
      "lineNumber": 813
    },
    {
      "declaringClass": "Bootstrap",
      "methodName": "loadInput",
      "fileName": "Bootstrap.java",
      "lineNumber": 47
    },
    {
      "declaringClass": "Example",
      "methodName": "main",
      "fileName": "Example.java",
      "lineNumber": 19
    }
  ],
  "name": "JsonSyntaxException",
  "message": "java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $"
}
```

### JSON input, plaintext output

Execute the following command:

```shell
./opwire-agent -p=8888 \
  --default-command="java -jar target/sample-cmdline-java-1.0.0-jar-with-dependencies.jar --input-format json --output-format text"
```

#### Valid input (a JSON object)

Make a HTTP request with `curl`:

```curl
curl -v \
  --request POST \
  --url 'http://localhost:8888/run?type=microservice&type=java' \
  --data '{
  "name": "Opwire",
	"url": "https://opwire.org/"
}'
```

Result:

```plain
> POST /run?type=microservice&type=java HTTP/1.1
> Host: localhost:8888
> User-Agent: curl/7.54.0
> Accept: */*
> Content-Length: 52
> Content-Type: application/x-www-form-urlencoded
> 
* upload completely sent off: 52 out of 52 bytes
< HTTP/1.1 200 OK
< Content-Type: text/plain
< X-Exec-Duration: 0.486525
< Date: Fri, 15 Mar 2019 04:23:46 GMT
< Content-Length: 234
< 
{input={name=Opwire, url=https://opwire.org/}, OPWIRE_REQUEST={header={Accept=[*/*], Content-Length=[52], Content-Type=[application/x-www-form-urlencoded], User-Agent=[curl/7.54.0]}, query={type=[microservice, java]}, params=null}}
```

#### Invalid input (not a JSON object)

Make a HTTP request with `curl`:

```curl
curl -v \
  --request POST \
  --url 'http://localhost:8888/run?type=microservice&type=java' \
  --data 'Not a JSON object'
```

Result:

```plain
> POST /run?type=microservice&type=java HTTP/1.1
> Host: localhost:8888
> User-Agent: curl/7.54.0
> Accept: */*
> Content-Length: 17
> Content-Type: application/x-www-form-urlencoded
> 
* upload completely sent off: 17 out of 17 bytes
< HTTP/1.1 500 Internal Server Error
< Content-Type: text/plain
< X-Error-Message: exit status 1
< Date: Fri, 15 Mar 2019 04:25:42 GMT
< Content-Length: 179
< 
{stack=[Ljava.lang.StackTraceElement;@5fcfe4b2, name=JsonSyntaxException, message=java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $}
```

## License

MIT

See [LICENSE](LICENSE) to see the full text.