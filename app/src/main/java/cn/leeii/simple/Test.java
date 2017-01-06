package cn.leeii.simple;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Lee on 2016/12/30.
 */

public class Test {
    public static void main(String[] args) {
        Observable.just(new student("李毅", new String[]{"语文", "语文3", "语文2"}), new student("李毅2", new String[]{"语文1", "语文13", "语文12"}), new student("李毅2", new String[]{"语文2", "语文23", "语文22"}))
                .flatMap(new Func1<student, Observable<String[]>>() {
                    @Override
                    public Observable<String[]> call(student student) {

                        return Observable.just(student.kc);
                    }
                })
                .flatMap(new Func1<String[], Observable<String>>() {
                    @Override
                    public Observable<String> call(String[] strings) {
                        return Observable.from(strings);
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        })
        ;
    }

    static class student {
        String name;
        String[] kc;

        public student(String name, String[] kc) {
            this.name = name;
            this.kc = kc;
        }
    }
}
