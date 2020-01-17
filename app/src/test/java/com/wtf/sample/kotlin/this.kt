package com.wtf.sample.kotlin

import androidx.lifecycle.Observer
import com.wtf.sample.db.entity.UserEntity

/**
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-17 下午4:18
 */
class A { // 隐式标签 @A
    inner class B { // 隐式标签 @B
        fun Int.foo() { // 隐式标签 @foo
            val a = this@A // A 的 this
            val b = this@B // B 的 this

            val c = this // foo() 的接收者，一个 Int
            val c1 = this@foo // foo() 的接收者，一个 Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit 的接收者
            }

            val funLit2 = { s: String ->
                // foo() 的接收者，因为它包含的 lambda 表达式
                // 没有任何接收者
                val d1 = this
            }
        }

        var observers = Observer<UserEntity> {
            var x = this
        }
        var name = lambda@ fun String.() {
            var x = this
        }

    }
}