(*=============================libcsv.ml=========================================*)  
(* load_csv: string -> string list list
Lit/charge en mémoire un fichier de données CSV (séparé par des virgules) *)
let load_csv filename : string list list =
  Csv.load ~separator:';' filename

(* lines_columns: string list list -> int * int
   Retourne le (nombre de lignes exact, nombre de colonnes maximales) d'un CSV;
   il est possible que certaines lignes comportent moins de colonnes. *)
let lines_columns (data : string list list) =
  (Csv.lines data, Csv.columns data)

(* Rend les données CSV rectangulaires. Cela remplit chaque ligne avec
   des cellules vides afin que toutes les lignes aient la même
   longueur que la ligne la plus longue. Après cette opération, chaque
   ligne aura une longueur égale à (snd (lines_columns data)) *)
let make_square (data : string list list) : string list list =
  Csv.square data

(* Renvoie true si le CSV est rectangulaire.
   Cela signifie que chaque ligne a le même nombre de cellules. *)
let is_square (data : string list list) =
  Csv.is_square data

(* map_csv: (string -> string) -> string list list -> string list list
   Applique une fonction à toutes les cellules du CSV et renvoie le résultat. *)
let map_csv (f : string -> string) (data : string list list) : string list list =
  Csv.map ~f:f data

(* save_csv: string -> string list list -> unit
   Enregistre dans le fichier spécifié les données CSV *)
let save_csv filename (data : string list list) =
  Csv.save ~separator:';' ~quote_all:true filename data

(* print_readable: string list list -> unit
   Affiche le CSV (pour faciliter le débogage) *)
let print_readable (data : string list list) =
  Csv.print_readable data

(*===================================parse_cli.ml==================================*)
(* Délimiteur standard indiquant la fin des options *)
let end_options = "--"

(* split_args: string list -> string list * string list
   Découpe une liste de chaînes avant/après le délimiteur end_options :
   split_args ["-o"; "file"; "--"; "hello"] = (["-o"; "file"], ["hello"]) *)
let rec split_args l = match l with
  | [] -> ([], [])
  | s :: l -> if s = end_options then ([], l)
              else let l1, l2 = split_args l in
                   s :: l1, l2

(* get_args: unit -> string * string list * string list
   Parse les arguments en ligne de commande et renvoie un tuple :
   ("name-of-program", ["--the"; "--options"], ["the"; "other"; "args"]) *)
let get_args () =
  let all = Array.to_list Sys.argv in
  match all with
  | [] -> failwith "parse_cli"
  | s0 :: l -> let l1, l2 = split_args l in
               (s0, l1, l2)

(*=====================================crud_csv.ml==================================*)
let create file args =
  let data =
    if Sys.file_exists file then load_csv file
    else []
  in
  let new_data = data @ [args] in
  save_csv file new_data

let read file =
  let data = load_csv file in
  List.iter (fun line ->
    print_endline (String.concat ";" line)
  ) data

let delete file id_to_delete =
  let data = load_csv file in
  let new_data = List.filter (fun line ->
    match line with
    | id :: _ -> id <> id_to_delete
    | [] -> false
  ) data in
  save_csv file new_data

(*=================================libunix.ml============================*)
(* get_example_file: string -> string
   Renvoie le fichier du sous-dossier examples à partir du dossier de main.exe :
   get_example_file "musee.csv" = "./examples/musee.csv" ou bien
   get_example_file "musee.csv" = "../examples/musee.csv" si besoin... *)
let get_example_file name =

  let exe = Sys.executable_name in
  let ( / ) = Filename.concat in
  Filename.dirname exe / "examples" / name

(*=================================main===================================*)
let () =
  let _, options, args = get_args () in
  match options with
  | "-C" :: file :: _ ->
      create file args
  | "-R" :: file :: _ ->
      read file
  | "-D" :: file :: id :: _ ->
      delete file id    
  | _ ->
      print_endline "Option inconnue"
