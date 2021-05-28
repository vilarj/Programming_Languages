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
