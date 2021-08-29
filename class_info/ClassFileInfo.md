相应的，在.class文件当中，定义的字段，要遵循field_info的结构。
```
field_info {
    u2             access_flags; // 属性 private static 等
    u2             name_index; // 名称
    u2             descriptor_index; // 类型 ()I或()Ljava/lang/String;等
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

同样的，在.class文件当中，定义的方法，要遵循method_info的结构。
```
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

在method_info结构中，方法当中方法体的代码，是存在于attribute_info的Code属性结构中，其结构如下：
```
Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}
```