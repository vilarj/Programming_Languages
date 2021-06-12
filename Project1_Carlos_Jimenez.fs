let data = [9; 92; 3; 7; 36; 77; 13; 6; 11; 46; 19; 24; 30; 55; 6];

let rec quickSort list = 
    | [] -> []
    | pivot::rest -> let left, right = rest |> List.partition(fun i -> i < pivot) (qsort left) @ [pivot] @ qsort right

printfn "Original List:%A\nQuick Sort: %A" quickSort data