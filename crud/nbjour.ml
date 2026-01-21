(* nbjour.ml *)
let annee = int_of_string (Sys.argv.(1))
let mois = int_of_string (Sys.argv.(2))

let bissextile a = 
  let test1 = (a mod 4 = 0) && (a mod 100 <> 0) in 
  let test2 = a mod 400 = 0 in
  match test1 , test2 with
  | true , false -> true
  | false , true -> true
  | _ , _ -> false

let nbjour m a= 
  let bis = bissextile a in 
  if bis = true && m = 2 then 29 else
    match m with 
    | 1 | 3 | 5 | 7 | 8 | 10 | 12 -> 31
    | 2 -> 28
    | 4 | 6 | 9 | 11-> 30
    | _ -> -1

    
    (* On affiche le résultat pour que Java puisse le lire *)
let () = print_endline (string_of_int (nbjour mois annee))