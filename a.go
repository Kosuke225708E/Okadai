package main

import (
	"fmt"
	"math/rand"
	"sync"
	"time"
)

type Semaphore struct {
	tokens chan struct{}
}

// 新しいセマフォを作成
func NewSemaphore(limit int) *Semaphore {
	return &Semaphore{
		tokens: make(chan struct{}, limit),
	}
}


func (s *Semaphore) Acquire() {
	s.tokens <- struct{}{} 
}


func (s *Semaphore) Release() {
	<-s.tokens 
}


func Worker(id int, sem *Semaphore) {
	fmt.Printf("Worker %d is waiting to acquire the semaphore...\n", id)
	sem.Acquire() // セマフォを取得
	fmt.Printf("Worker %d has acquired the semaphore!\n", id)

	
	time.Sleep(time.Duration(rand.Intn(1000)) * time.Millisecond)

	fmt.Printf("Worker %d is releasing the semaphore...\n", id)
	sem.Release()
}

func main() {
	rand.Seed(time.Now().UnixNano())
	sem := NewSemaphore(3) 

	var wg sync.WaitGroup

	
	for i := 1; i <= 10; i++ {
		wg.Add(1)
		go func(id int) {
			defer wg.Done()
			Worker(id, sem)
		}(i)
	}

	wg.Wait() 
	fmt.Println("All workers have finished their tasks.")
}
