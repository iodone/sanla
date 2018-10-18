# SAN scala 实现

# SAN 基本格式描述

    # https://astrocorp.net/san/
    # This is a SAN document
    
    title = "SAN Example"
    
    creator = {
      name = "Sylvain Kerkour"
      website = "https://kerkour.com"
    }
    
    database = {
      server = "192.168.1.1"
      ports = [ 8001, 8001, 8002 ]
      connection_max = 5000
      enabled = true
    }
    
    servers = {
      # Indentation (only spaces) is allowed but not required
      alpha = {
        ip = "10.0.0.1"
        dc = "eqdc10"
      }
    
      beta = {
        ip = "10.0.0.2"
        dc = "eqdc10"
      }
    }
    
    empty_map = {}
    
    hosts = [
      "alpha",
      "omega",
    ]

# 实现思路

配置文件该是外部DSL的一种，可以考虑采用

1. Antlr 生成parser，采用g4语法格式
2. scala 解析器组合子

scala既然提供内置的parser功能，先可以从简单入手

SAN的BNF语法如下：

    	 san  ::= expr {"\n" expr}
         expr ::= string "=" value
    	value ::= string|int|float|arr|obj|"null"|"true"|"false"	
    	  arr ::= "[" [values] "]"
    	  obj ::= "{" [san] "}"
       values ::= value {"," value}
    	

# 拓展

用scala parser 写的sparkSQL: [https://github.com/stephentu/scala-sql-parser/blob/master/src/main/scala/parser.scala](https://github.com/stephentu/scala-sql-parser/blob/master/src/main/scala/parser.scala)

用Scala来构建DSL: [https://www.slidestalk.com/s/building_dsls_with_scala_rain_focus_u6kwsr](https://www.slidestalk.com/s/building_dsls_with_scala_rain_focus_u6kwsr)

用scala parser 或者 fastparse 来写 XQL