### Job的嵌套

1. 一个Job可以嵌套在另一个Job中, 被嵌套的Job称为子Job, 外部的Job称为父Job.
2. 子Job不能单独执行，需要由父Job来启动.

案例：创建两个Job, 作为子Job, 再创建一个Job作为父Job.