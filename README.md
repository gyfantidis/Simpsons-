# Simpsons-
Threads work

Ζητούμενο

![image](https://user-images.githubusercontent.com/96373640/228656601-a56b9f74-8d46-4298-95e0-cadad537f32e.png)



![image](https://user-images.githubusercontent.com/96373640/228656657-c5bcda17-de6c-44f4-b1e2-ffb1dd1b46b0.png)

Τώρα πάμε στην κλάση ProcessThread, εισάγουμε το κομμάτι της λίστας, και γίνεται αρχικά ένας έλεγχος για γραμμές με λάθος, εισάγουμε τις στήλες που χρειαζόμαστε και τρέχουμε τις μεθόδους που γίνονται οι επεξεργασίες που θέλουμε, 
Α) μέθοδος proccesEpisodeWords παράγει το HasMap, episodeWords, το οποίο περιέχει τον αριθμό των λέξεων του κάθε επεισοδίου
Β) μέθοδος proccesLocationText παράγει το HasMap, locationText, το οποίο περιέχει τον αριθμό των στιχομυθιών της κάθε τοποθεσίας
Γ) μέθοδος proccesCharacterWords, η οποία αρχικά ξεχωρίζει τις λέξεις της κάθε στιχομυθίας, ελέγχει είναι πάνω από 4 γράμματα, και παράγονται 4 HasMaps, έναν για κάθε χαρακτήρα, στα οποία περιέχονται οι λέξεις και πόσες φορές ειπώθηκαν,
Πχ HasMap bartWords, περιέχει τις λέξεις με το πόσες φορές ειπώθηκαν.
Σε αυτό το σημείο να παραδεχτώ πως δεν κατάφερα να δουλέψω με ένα HasMap το οποίοι θα περιείχε HasMap, και αποφάσισα να λειτουργήσω με HasMap με String και int.
	
Πίσω στην main, θα πρέπει να εννόσουμε τα νήματα χωρίς να απολέσουμε δεδομένα, αυτό γίνεται με ένα loop και την παρακάτω συνάρτηση


![image](https://user-images.githubusercontent.com/96373640/228656724-81325aef-ce7a-4fbc-8c97-7628cf3437f2.png)



![image](https://user-images.githubusercontent.com/96373640/228656776-720739b4-3c7e-4351-b41b-db30394c6473.png)


![image](https://user-images.githubusercontent.com/96373640/228656818-639669f1-39e7-4aa7-8b91-b81e3d5f4c05.png)

