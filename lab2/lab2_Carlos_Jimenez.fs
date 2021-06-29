open System.Numerics // need to use bigint

// Problem 1
let cubes = List.map (fun i -> i * i * i) [1;2;3]
printfn "Problem 1\n%A^3 = %A" [1;2;3] cubes

// Problem 2
let rec factorial (number : int) =
    match number with
    | 0 | 1 -> bigint.One
    | _ -> (bigint number) * factorial (number - 1)

let c (n : int) (k : int) = 
    let result = 
        if n = 0 then bigint.Zero
        else (factorial n) / ((factorial k * factorial(n - k)))
    printfn "%A" result

printfn "\nProblem 2"
c 10 5

// Problem 3
let rec vecadd a b =
    match a, b with
    | [], [] -> []
    | a::atail, b::btail -> (a + b) :: (vecadd atail btail)
    
printfn "\nProblem 3\n%A(i) + %A(k) = %A(i+k)" [1;2;3] [4;5;6] (vecadd [1;2;3] [4;5;6])

// Problem 4
let m1 = [[1;2;3];[4;5;6]]
let m2 = [[7;8;9];[1;2;3]]

let rec matadd m1 m2 =
    match m1, m2 with
    | [], [] -> []
    | m1::matrix1, m2::matrix2 -> (vecadd m1 m2) :: (matadd matrix1 matrix2)
            
printfn "Problem 4\nM1 = %A\nM2 = %A\n\nM1 + M2 = %A" m1 m2 (matadd m1 m2)