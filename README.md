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

Install dependencies and build packages:

```shell
mvn clean package
```

Download and extract the latest [`opwire-agent`](https://github.com/opwire/opwire-agent/releases/latest) program into this directory:

![project-home-dir](https://raw.github.com/opwire/sample-cmdline-java/master/docs/assets/images/ls.png)

## Test the service using a web browser

Execute the following command:

```shell
./opwire-agent serve
```

Open this URL `http://localhost:8888/$?type=microservice&type=java`:

![example-output](https://raw.github.com/opwire/sample-cmdline-java/master/docs/assets/images/example.png)


## Test the service using `curl`

### Default data format (`json`)

Execute the following command:

```shell
./opwire-agent serve -p=8888 --default-command="java -jar target/sample-cmdline-java-1.0.0-all.jar"
```

#### Valid input (a JSON object)

Make a HTTP request with `curl`:

```curl
curl -v --request POST \
  --url 'http://localhost:8888/%24?type=microservice&type=java' \
  --header 'opwire-execution-timeout: 1.5s' \
  --header 'opwire-explain-failure: 1' \
  --header 'opwire-explain-success: 1' \
  --header 'opwire-request-id: 34c8afe2-3b21-485e-b3f5-034907352516' \
  --data '{
  "name": "Opwire",
  "url": "https://opwire.org/"
}'
```

Result:

```plain
> POST /$?type=microservice&type=java HTTP/1.1
> User-Agent: curl/7.35.0
> Host: localhost:8888
> Accept: */*
> opwire-execution-timeout: 1.5s
> opwire-explain-failure: 1
> opwire-explain-success: 1
> opwire-request-id: 34c8afe2-3b21-485e-b3f5-034907352516
> Content-Length: 54
> Content-Type: application/x-www-form-urlencoded
> 
* upload completely sent off: 54 out of 54 bytes
< HTTP/1.1 205 Reset Content
< Content-Type: text/plain
< X-Exec-Duration: 0.187978
< Date: Tue, 09 Apr 2019 06:14:35 GMT
< Transfer-Encoding: chunked
< 

[edition------------------------------------------------------------------------
{
  "revision": "3de2f29",
  "version": "v1.0.6-34-g3de2f29"
}
------------------------------------------------------------------------edition]

[request------------------------------------------------------------------------
{
  "header": {
    "Accept": [
      "*/*"
    ],
    "Content-Length": [
      "54"
    ],
    "Content-Type": [
      "application/x-www-form-urlencoded"
    ],
    "Opwire-Execution-Timeout": [
      "1.5s"
    ],
    "Opwire-Explain-Failure": [
      "1"
    ],
    "Opwire-Explain-Success": [
      "1"
    ],
    "Opwire-Request-Id": [
      "34c8afe2-3b21-485e-b3f5-034907352516"
    ],
    "User-Agent": [
      "curl/7.35.0"
    ]
  },
  "method": "POST",
  "params": null,
  "path": "/$",
  "query": {
    "type": [
      "microservice",
      "java"
    ]
  }
}
------------------------------------------------------------------------request]

[command------------------------------------------------------------------------
{
  "provided": {
    "method": "POST",
    "resource": "",
    "timeout": 1.5
  },
  "resolved": {
    "command": "java -jar target/sample-cmdline-java-1.0.0-all.jar",
    "method": null,
    "resource": ":default-resource:",
    "timeout": 1.5
  }
}
------------------------------------------------------------------------command]

[settings-----------------------------------------------------------------------
{
  "MYSQL_PASSWORD": "root",
  "MYSQL_URL": "mysql://localhost:3306",
  "MYSQL_USERNAME": "root"
}
-----------------------------------------------------------------------settings]

[stdin--------------------------------------------------------------------------
{
  "name": "Opwire",
  "url": "https://opwire.org/"
}
--------------------------------------------------------------------------stdin]

[stdout-------------------------------------------------------------------------
{
  "OPWIRE_EDITION": {
    "revision": "3de2f29",
    "version": "v1.0.6-34-g3de2f29"
  },
  "input": {
    "name": "Opwire",
    "url": "https://opwire.org/"
  },
  "OPWIRE_SETTINGS": {
    "MYSQL_PASSWORD": "root",
    "MYSQL_URL": "mysql://localhost:3306",
    "MYSQL_USERNAME": "root"
  },
  "OPWIRE_REQUEST": {
    "method": "POST",
    "path": "/$",
    "header": {
      "Accept": [
        "*/*"
      ],
      "Content-Length": [
        "54"
      ],
      "Content-Type": [
        "application/x-www-form-urlencoded"
      ],
      "Opwire-Execution-Timeout": [
        "1.5s"
      ],
      "Opwire-Explain-Failure": [
        "1"
      ],
      "Opwire-Explain-Success": [
        "1"
      ],
      "Opwire-Request-Id": [
        "34c8afe2-3b21-485e-b3f5-034907352516"
      ],
      "User-Agent": [
        "curl/7.35.0"
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

-------------------------------------------------------------------------stdout]
* Connection #0 to host localhost left intact
```

#### Invalid input (not a JSON object)

Make a HTTP request with `curl`:

```curl
curl -v \
  --request POST \
  --url 'http://localhost:8888/$?type=microservice&type=java' \
  --data 'Not a JSON object'
```

Result:

```plain
> POST /$?type=microservice&type=java HTTP/1.1
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
./opwire-agent serve -p=8888 \
  --default-command="java -jar target/sample-cmdline-java-1.0.0-all.jar --input-format json --output-format text"
```

#### Valid input (a JSON object)

Make a HTTP request with `curl`:

```curl
curl -v \
  --request POST \
  --url 'http://localhost:8888/$?type=microservice&type=java' \
  --data '{
  "name": "Opwire",
	"url": "https://opwire.org/"
}'
```

Result:

```plain
> POST /$?type=microservice&type=java HTTP/1.1
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
  --url 'http://localhost:8888/$?type=microservice&type=java' \
  --data 'Not a JSON object'
```

Result:

```plain
> POST /$?type=microservice&type=java HTTP/1.1
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

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b your-new-feature`)
3. Commit your changes (`git commit -am "Add some feature"`)
4. Push to the branch (`git push origin your-new-feature`)
5. Create new Pull Request

## License

MIT

See [LICENSE](LICENSE) to see the full text.
