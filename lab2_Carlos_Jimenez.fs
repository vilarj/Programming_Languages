// Problem 1
let cubes = List.map (fun i -> i * i * i) [1;2;3]
printfn "Problem 1\n%A^3 = %A" [1;2;3] cubes

// Problem 2
let f n =
    match n with
    | _ when n <= 1. -> 1.
    | _ -> [1. ..n] |> Seq.reduce (*)

let (!) = f
let nf n k = !n / (!k * !(n - k))

printfn "\nProblem 2\nC(10, 5) = %A"  <| ``nf`` 10. 5.

// Problem 3
let rec vecadd a b =
    match a, b with
    | [], [] -> []
    | a::atail, b::btail -> (a + b) :: (vecadd atail btail)
    
printfn "\nProblem 3\n%A(i) + %A(k) = %A(i+k)" [1;2;3] [4;5;6] (vecadd [1;2;3] [4;5;6])

// Problem 4
let m1 = [[1;4],[2;5],[3;6]]
let m2 = [[7;8;9],[1;2;3]]

let rec matadd a b =
    match a, b with
    | [], [] -> []
    | a::atail, b::btail -> (a*b) :: vecadd atail btail
    | _ ->