let rec partage liste = match liste with 
  | [] -> [],[]
  | [t] -> [t],[]
  | t1 :: t2 :: l -> let q1,q2 = partage l in t1::q1 ,t2:: q2
                                                      
let rec merge l1 l2 = match l1,l2 with
  | [],[] -> []
  | l,[] -> l
  | [],l -> l
  | t1::l1 , t2::l2 -> if t2 < t1 then t2 :: (merge (t1::l1) l2)
      else t1:: merge l1 (t2 :: l2)
             
let rec triFusion liste = match liste with 
  | [] -> [] 
  | [e] -> [e]
  | l -> let q1,q2 = partage liste 
      in merge (triFusion(q1)) (triFusion(q2))

let () =
  if Array.length Sys.argv > 1 then
    let entree = Sys.argv.(1) in
    (* 1. Découpage de la chaîne "Nom1;Nom2" en liste ["Nom1"; "Nom2"] *)
    let liste_noms = String.split_on_char ';' entree in
    
    (* 2. Tri avec ton algorithme *)
    let liste_triee = triFusion liste_noms in
    
    (* 3. Reconstruction de la chaîne "Nom1;Nom2" triée *)
    let sortie = String.concat ";" liste_triee in
    
    (* 4. Envoi vers Java *)
    print_endline sortie