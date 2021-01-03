/*
 * Copyright (c) 2019.  黄钰朝
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hyc.www.test;

/**
 * @program XHotel
 * @description 用于测试正则表达式
 */
public class TestRegex {
    public static void main(String[] args) {
        String regex = "[\\w_]{6,20}$";
        System.out.println(regex.matches(""));
        System.out.println("tdsflj14321fasdf\n".matches(regex));
        String regex1= "login*";

        System.out.println("alogin".matches(regex1));
    }
}
