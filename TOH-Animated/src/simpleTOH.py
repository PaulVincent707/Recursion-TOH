def TowerOfHanoi(n , from_rod, to_rod, aux_rod): 
    if n == 1: 
        print("Move disk 1 from rod",from_rod,"to rod",to_rod)
        return
    TowerOfHanoi(n-1, from_rod, aux_rod, to_rod)
    print("Move disk",n,"from rod",from_rod,"to rod",to_rod)
    TowerOfHanoi(n-1, aux_rod, to_rod, from_rod)

n = 10
TowerOfHanoi(n,'A','C','B')

online Python - https://www.onlinegdb.com/online_python_interpreter