# LadybugFlow

![](https://img.shields.io/badge/license-Apache2.0-yellow)
![](https://img.shields.io/badge/Java-1.8-orange)

### 1. 使用方法

#### 1.1. 导入依赖

##### Maven

```
<!-- https://mvnrepository.com/artifact/io.github.nobuglady/ladybugflow -->
<dependency>
    <groupId>io.github.nobuglady</groupId>
    <artifactId>ladybugflow</artifactId>
    <version>0.0.3</version>
</dependency>
```

##### Gradle
```
// https://mvnrepository.com/artifact/io.github.nobuglady/ladybugflow
implementation 'io.github.nobuglady:ladybugflow:0.0.3'
```
#### 1.2. 程序配置

您需要写一个类继承自FlowRunner，和一个描述Flow流程的json文件，这两个文件需要放在相同的包下面

```
src/main/java
- test
  + MyFlow1.java
src/main/resources
- test
  + MyFlow1.json
```

<details>
<summary> <b>MyFlow1.java</b> </summary>

```
public class MyFlow1 extends FlowRunner {
 
    @Node (label=  "a" )
    public void process_a() {
        System.out.println(  "processing a" );
    }
   
    @Node (label=  "b" )
    public void process_b() {
        System.out.println(  "processing b" );
    }
   
    @Node (label=  "c" )
    public void process_c() {
        System.out.println(  "processing c" );
    }
   
    @Node (label=  "d" )
    public void process_c() {
        System.out.println(  "processing d" );
    }
}
```

</details>


<details>
<summary><b>MyFlow1.json</b></summary>

```
{
	"flowId": "123",
	"nodes": [
		{
			"id": "1",
			"label": "a"
		},
		{
			"id": "2",
			"label": "b"
		},
		{
			"id": "0b5ba9df-b6c7-4752-94e2-debb6104015c",
			"label": "c"
		},
		{
			"id": "29bc32c7-acd8-4893-9410-e9895da38b2e",
			"label": "d"
		}
	],
	"edges": [
		{
			"id": "1",
			"from": "1",
			"to": "2",
			"arrows": "to"
		},
		{
			"id": "078ffa82-5eff-4d33-974b-53890f2c9a18",
			"from": "1",
			"to": "0b5ba9df-b6c7-4752-94e2-debb6104015c",
			"arrows": "to"
		},
		{
			"id": "90663193-7077-4aca-9011-55bc8745403f",
			"from": "2",
			"to": "29bc32c7-acd8-4893-9410-e9895da38b2e",
			"arrows": "to"
		},
		{
			"id": "a6882e25-c07a-4abd-907e-e269d4eda0ec",
			"from": "0b5ba9df-b6c7-4752-94e2-debb6104015c",
			"to": "29bc32c7-acd8-4893-9410-e9895da38b2e",
			"arrows": "to"
		}
	]
}
```

</details>

#### 1.3. 图形工具
有一个图形工具可以帮助您生成Flow描述的json文件。

画流程图
<img src="https://github.com/nobuglady/nobuglady-network/blob/main/readme/4.gif?raw=true" alt="" width="250px"/>

将流程图转化成json
<img src="https://github.com/nobuglady/nobuglady-network/blob/main/readme/5.gif?raw=true" alt="" width="250px"/>

#### 1.4. 启动
通过下面的代码可以启动您定义的flow
```
MyFlow1 myFlow1 =  new MyFlow1();
myFlow1.startFlow();
```
#### 1.5. 停止
系统停止的时候需要调用下面的代码来停止flow管理系统
```
FlowStarter.shutdown();
```

### 2. 运行结果

#### 2.1. 成功日志


<details>
<summary>下面是运行成功的日志文件</summary>

```
Ready queue thread started.
Complete queue thread started.
json:
{"flowId":"123","nodes":[{"id":"1","label":"a"},{"id":"2","label":"b"},{"id":"0b5ba9df-b6c7-4752-94e2-debb6104015c","label":"c"},{"id":"29bc32c7-acd8-4893-9410-e9895da38b2e","label":"d"}],"edges":[{"id":"1","from":"1","to":"2","arrows":"to"},{"id":"078ffa82-5eff-4d33-974b-53890f2c9a18","from":"1","to":"0b5ba9df-b6c7-4752-94e2-debb6104015c","arrows":"to"},{"id":"90663193-7077-4aca-9011-55bc8745403f","from":"2","to":"29bc32c7-acd8-4893-9410-e9895da38b2e","arrows":"to"},{"id":"a6882e25-c07a-4abd-907e-e269d4eda0ec","from":"0b5ba9df-b6c7-4752-94e2-debb6104015c","to":"29bc32c7-acd8-4893-9410-e9895da38b2e","arrows":"to"}]}
execute:1
node name:a
processing a
execute:2
node name:b
processing b
execute:0b5ba9df-b6c7-4752-94e2-debb6104015c
node name:c
processing c
execute:29bc32c7-acd8-4893-9410-e9895da38b2e
node name:d
processing d
Complete success.
json:
{"nodes":[{"id": "1","label": "a" ,"color": "#36AE7C"},{"id": "2","label": "b" ,"color": "#36AE7C"},{"id": "0b5ba9df-b6c7-4752-94e2-debb6104015c","label": "c" ,"color": "#36AE7C"},{"id": "29bc32c7-acd8-4893-9410-e9895da38b2e","label": "d" ,"color": "#36AE7C"}],"edges":[{"id": "1","from": "1","to": "2","arrows": "to"},{"id": "078ffa82-5eff-4d33-974b-53890f2c9a18","from": "1","to": "0b5ba9df-b6c7-4752-94e2-debb6104015c","arrows": "to"},{"id": "90663193-7077-4aca-9011-55bc8745403f","from": "2","to": "29bc32c7-acd8-4893-9410-e9895da38b2e","arrows": "to"},{"id": "a6882e25-c07a-4abd-907e-e269d4eda0ec","from": "0b5ba9df-b6c7-4752-94e2-debb6104015c","to": "29bc32c7-acd8-4893-9410-e9895da38b2e","arrows": "to"}]}
```
</details>

After the process is ended, you can get the execution result json in the last line.
Paste json to the following location can check the status of the flow. 
(green:success, red:error, white:waiting).

<img src="https://github.com/nobuglady/nobuglady-network/blob/main/readme/2.gif?raw=true" alt="" width="250px"/>

#### 2.2. 错误日志


<details>
<summary>下面是运行错误的日志文件</summary>

```
Ready queue thread started.
Complete queue thread started.
json:
{"flowId":"123","nodes":[{"id":"1","label":"a"},{"id":"2","label":"b"},{"id":"0b5ba9df-b6c7-4752-94e2-debb6104015c","label":"c"},{"id":"29bc32c7-acd8-4893-9410-e9895da38b2e","label":"d"}],"edges":[{"id":"1","from":"1","to":"2","arrows":"to"},{"id":"078ffa82-5eff-4d33-974b-53890f2c9a18","from":"1","to":"0b5ba9df-b6c7-4752-94e2-debb6104015c","arrows":"to"},{"id":"90663193-7077-4aca-9011-55bc8745403f","from":"2","to":"29bc32c7-acd8-4893-9410-e9895da38b2e","arrows":"to"},{"id":"a6882e25-c07a-4abd-907e-e269d4eda0ec","from":"0b5ba9df-b6c7-4752-94e2-debb6104015c","to":"29bc32c7-acd8-4893-9410-e9895da38b2e","arrows":"to"}]}
execute:1
node name:a
processing a
execute:2
node name:b
processing b
execute:0b5ba9df-b6c7-4752-94e2-debb6104015c
node name:c
processing c
java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at io.github.nobuglady.network.fw.FlowRunner.execute(FlowRunner.java:49)
	at io.github.nobuglady.network.fw.executor.NodeRunner.run(NodeRunner.java:93)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.lang.RuntimeException: test
	at io.github.nobuglady.network.MyFlow1.process_b(MyFlow1.java:16)
	... 11 more
java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at io.github.nobuglady.network.fw.FlowRunner.execute(FlowRunner.java:49)
	at io.github.nobuglady.network.fw.executor.NodeRunner.run(NodeRunner.java:93)
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:834)
Caused by: java.lang.RuntimeException: test
	at io.github.nobuglady.network.MyFlow1.process_b(MyFlow1.java:16)
	... 11 more
Complete error.
json:
{"nodes":[{"id": "1","label": "a" ,"color": "#36AE7C"},{"id": "2","label": "b" ,"color": "#EB5353"},{"id": "0b5ba9df-b6c7-4752-94e2-debb6104015c","label": "c" ,"color": "#36AE7C"},{"id": "29bc32c7-acd8-4893-9410-e9895da38b2e","label": "d" ,"color": "#E8F9FD"}],"edges":[{"id": "1","from": "1","to": "2","arrows": "to"},{"id": "078ffa82-5eff-4d33-974b-53890f2c9a18","from": "1","to": "0b5ba9df-b6c7-4752-94e2-debb6104015c","arrows": "to"},{"id": "90663193-7077-4aca-9011-55bc8745403f","from": "2","to": "29bc32c7-acd8-4893-9410-e9895da38b2e","arrows": "to"},{"id": "a6882e25-c07a-4abd-907e-e269d4eda0ec","from": "0b5ba9df-b6c7-4752-94e2-debb6104015c","to": "29bc32c7-acd8-4893-9410-e9895da38b2e","arrows": "to"}]}
```

</details>

流程运行结束后，会输出描述流程状态的json字符串，可以把它粘贴到图形化工具里查看流程的运行状态。
(green:success, red:error, white:waiting).

<img src="https://github.com/nobuglady/nobuglady-network/blob/main/readme/3.gif?raw=true" alt="" width="250px"/>
