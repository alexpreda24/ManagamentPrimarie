Am creat un fisier pentru fiecare tip de Utilizator:Angajat,Elev,EntitateJuridica,Pensionar si Persoana.
Fiecare tip de utilizator va contine getteri si setteri pentru numele utilizatorului,cat si pentru alte 
variabile specifice lui. Vor contine fiecare cate un set de cereri neparcurse si o lista cu cereri finalizate.

Pentru functia "adauga utilizator",vom crea cate un utilizator specific,apoi il vom cauta in set-ul de
utilizatori specifici. Fiecare utilizator poate sa faca oricate cereri doreste,astfel de fiecare data cand acesta 
vrea sa depuna o noua cerere, se va verifica daca tipul utilizatorului poate inainta o cerere de tipul 
acela. Pentru cereri in asteptare doar afisez cererile inainte la Primarie.Pentru a retrage o cerere,caut 
cererea ce trebuie eliminata si apoi o sterg.Pentru a adauga un functionar in setul de functionari publici,tot
ce trebuie facut este sa construim o variabila cu ajutorul constructorului specific si apoi sa-l adaug in set-ul 
de functionari.

Cand de la tastatura este introdus "rezolva_cerere". In fisierul FunctionarPublic.java metoda ca sa rezolv o cerere
si sa o elimin din cererile nefinalizate specifice unui tip de utilizator,am folosit o metoda care se foloseste de un 
set generic.

La sfarsit vom elibera memoria.
Tema este realizata in totalitate.
