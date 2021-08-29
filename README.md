# DxKit： 基于ASM + transform 实现AOP功能的kit

### 1. 支持查看所有点击View的类名和id
add this code into build.gradle
```groovy
apply plugin: 'dxkit-click'
clickExtension {
    canSeeClickId = true
    seeIdClass = "com/dxkit/library/utils/SeeViewId"
    seeIdMethod = "seeViewIdName"
}
```

### 2. 支持click粘连响应拦截
add this code into build.gradle
```groovy
apply plugin: 'dxkit-click'
clickExtension {
    isForbidStickClick = true
    stickClickClass = "com/dxkit/library/utils/FastClickUtil"
    stickClickMethod = "isFastDoubleClick"
}
```

### 3. 支持为指定类的方法添加try catch保护，支持自己代码、第三方jar、第三方aar
add this code into build.gradle
```groovy
    apply plugin: 'dxkit-tryCatch'
    tryCatchExtension {
    // 需要加try catch的类及其中的方法信息
    methodMap = ["com/dxkit/demo/test/CrashTest": ["crashMethod1", "crashMethod2",
                                                         "getInt", "isEnable", "getObj", "getStr", "getChar", "show",
                                                         "getFloat", "getByte", "getDouble", "getShort", "getLong",
                                                         "getArrayObj", "getArrayObj2", "getArrayInt", "getArrayInt2",
                                                         "getArrayBoolean", "getArrayBoolean2", "getList",
                                                        ],

                 "com/dx/tracer/util/NetUtil"  : ["getNetworkType", "getCrashString",],
                 "com/dx/tracer/FrameFloatView": ["getExtra",],
    ]

    // 用户处理异常信息的类和方法
    exceptionHandler = ["com/dxkit/library/utils/ExceptionHandler": "handleException"]
}
```

### ...