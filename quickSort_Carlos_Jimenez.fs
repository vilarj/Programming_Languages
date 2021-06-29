let data = [9; 92; 3; 7; 36; 77; 13; 6; 11; 46; 19; 24; 30; 55; 6];

let quickSort list = 
  let rec qs list cont =
    match list with
    | [] -> cont []
    | pivot::rest -> 
      let left, right = rest |> List.partition(fun i -> i < pivot)
      qs left (fun Left -> 
      qs right (fun Right -> cont(Left@pivot::Right)))
  qs list (fun x -> x)

printfn "Original List: %A\nQuick Sort: %A" data (quickSort data)