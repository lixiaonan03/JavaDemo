package thread;

import util.TimeUtil;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lixiaonan
 * 功能描述: 线程顺序执行执行的8种方法的
 * 时 间： 2022/8/29 19:18
 */
public class ThreadTest1 {

    public static void main(String[] args) {
        int i = 8;
        switch (i) {
            case 1:
                join();
                break;
            case 2:
                join1();
                break;
            case 3:
                wait1();
                break;
            case 4:
                threadPool();
                break;
            case 5:
                condition();
                break;
            case 6:
                countDown();
                break;
            case 7:
                barrier();
                break;
            case 8:
                semaphore();
                break;
            default:
                break;
        }
    }

    private static void join() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("产品经理规划新需求");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                syo("开发人员开发新需求功能");
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                syo("测试人员测试功能");
            }
        });
        thread3.start();
        thread1.start();
        thread2.start();

    }

    /**
     * 通过主线程的join 方法执行的
     */
    private static void join1() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("产品经理规划新需求");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("开发人员开发新需求功能");
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("测试人员测试功能");
            }
        });
        try {
            thread1.start();
            //main 线程调用1的 join 保证1执行完之后再往下走
            thread1.join();
            thread2.start();
            thread2.join();
            thread3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过wait 方式实现的
     */
    private static void wait1() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    syo("产品经理规划新需求");
                    lock1.notify();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        syo("开发人员开发新需求功能");
                        lock1.wait();
                        synchronized (lock2) {
                            lock2.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    try {
                        syo("测试人员测试功能");
                        lock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }

    /**
     * 线程池的方式
     */
    private static void threadPool() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("产品经理规划新需求");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("开发人员开发新需求功能");
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("测试人员测试功能");
            }
        });
        executorService.submit(thread1);
        executorService.submit(thread2);
        executorService.submit(thread3);
    }

    /**
     * Condition是一个多线程间协调通信的工具类，使得某个，或者某些线程一起等待某个条件（Condition）,只有当该条件具备( signal 或者 signalAll方法被调用)时 ，这些等待线程才会被唤醒，从而重新争夺锁。
     */
    private static void condition() {
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                syo("产品经理规划新需求");
                condition1.signal();
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                syo("开发人员开发新需求功能");
                condition2.signal();
                lock.unlock();


            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    condition2.await();
                    syo("测试人员测试功能");
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread3.start();
        thread2.start();
        thread1.start();
    }

    private static void countDown() {
        CountDownLatch c1 = new CountDownLatch(1);
        CountDownLatch c2 = new CountDownLatch(1);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("产品经理规划新需求");
                c1.countDown();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                syo("开发人员开发新需求功能");
                c2.countDown();


            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    c2.await();
                    syo("测试人员测试功能");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread3.start();
        thread2.start();
        thread1.start();
    }

    /**
     * 通过设置屏障的方式执行的
     */
    private static void barrier() {
        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    syo("产品经理规划新需求");
                    cyclicBarrier1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier1.await();
                    syo("开发人员开发新需求功能");
                    cyclicBarrier2.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier2.await();
                    syo("测试人员测试功能");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread3.start();
        thread2.start();
        thread1.start();
    }

    /**
     * 通过信号量变化的
     */
    private static void semaphore() {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                syo("8产品经理规划新需求");
                semaphore1.release();

            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore1.acquire();
                    syo("8开发人员开发新需求功能");
                    semaphore2.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore2.acquire();
                    syo("8测试人员测试功能");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread3.start();
        thread2.start();
        thread1.start();
    }

    public static void syo(String obj) {
        System.out.println(Thread.currentThread().getName() + "==" + obj + "===" + TimeUtil.getNowTimeForStr());
    }
}
