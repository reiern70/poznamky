# poznamky
= GIT howto =

== Uzitocne linky ==

*http://ndpsoftware.com/git-cheatsheet.html
*http://www.root.cz/clanky/git-distribuovana-sprava-revizi/
*http://www.kernel.org/pub/software/scm/git/docs/gittutorial.html
*\\devhome\doc\SW_produkty\pro-git.pdf
*hezky tutorial pro spolupraci vice lidi na prikladu Githubu: http://www.eqqon.com/index.php/Collaborative_Github_Workflow
*http://cheat.errtheblog.com/s/git
*http://www.gitready.com/
*http://wiki.eclipse.org/Git#Git_mirrors_of_CVS_repositories
*http://www-cs-students.stanford.edu/~blynn/gitmagic/ch02.html#_reverting
*http://book.git-scm.com/4_undoing_in_git_-_reset,_checkout_and_revert.html
*http://www.slideshare.net/chacon/git-101-presentation (dukladna prezentace o pouzivani i vnitrnostech gitu)
*http://nvie.com/posts/a-successful-git-branching-model/ '''GIT branching strategy and release management'''
*http://yakiloo.com/getting-started-git-flow/ GIT flow process (nastavenie command line gitu pre pouzitie git flow)
*http://www.kernel.org/pub/software/scm/git/docs/git-submodule.html Manualova stranka ke git submodulum
*https://git.wiki.kernel.org/index.php/GitSubmoduleTutorial Popis spravy submodulu

== Pristup do GIT ==

Na pristup do GIT je potrebne:

*vygenerovat si par SSH klucov
*dat public key adminovi devhome (L. Babjak), on vytvori na poziadanie ucet na devhome

=== Vygenerovanie SSH klucov ===

Na Windows je potreba otevrit <tt>git-bash</tt> - pri plne instalaci je tato moznost v kontextovem menu pruzkumnika, pri portable distribuci se spusti <tt>''<git_install_dir>''\git-bash.bat</tt>.

Potrebujeme vygenerovat kluce ssh - ak este nemate. Odporucany postup:

 ssh-keygen -t dsa

Pripadne s vlastnim emailem:

 ssh-keygen -C "user@example.com" -t rsa

To ci zadate passphrasu, alebo nie je vase vec, ale pamatajte, ze ju budete musiet zadavat pri kazdom pouziti gitu, ak si to o5 niecim neautomatizujete, ako je napriklad ssh-agent. Kluce najdeme v adresary '''.ssh''' vo svojom home. Verejny kluc, ktory mozte vystavit kde len chcete konci na '''.pub'''. Privatny tuto "priponu" nema. Ten si rozhodne chrante ako oko v hlave&nbsp;;-).

Ak pouzivate '''putty''', tak vam moze pomoct toto: http://www.madzik.sk/autentif_ssh/autentifikacia_ssh.html

=== Autentifikacia verejnym klucom pre GitBash pre vybrany server ===

# na server (napr. devhome) treba nakopirovat svoj verejny kluc (SYSINGSUN)
# v PuTTyGen si treba otvorit svoj sukromny kluc (nieco ako private.ppk)
# MENU -> Conversions -> Export OpenSSH key
# tento openSSH kluc treba ulozit do '''%GIT_HOME%/.ssh/''' (vieme zistit pomocou echo $HOME) ako '''id_rsa'''
# do priecinku na predmetnom serveri '''%NASE_TB%/.ssh/''' nakopirovat subor authorized_keys, v ktorom je nas public key

== Zakladne prikazy ==

*naklonovat si repozitar ku sebe (toto vyrobi kompletnu kopiu repozitara)
<pre>git clone ssh://miko@devhome/git/ibanking .</pre>
*switch na phone branch (prvy krat po clone-e)
<pre>git checkout --track -b phone remotes/origin/phone</pre>
*switch medzi branch-mi
<pre>git.exe checkout master
   resp.
   git.exe checkout phone
</pre>
*pridanie zmien do lokalneho repozitara
<pre>git add
   git commit  # POZOR! toto je komit len do lokalneho repa
</pre>
*publikovat zmeny do zdielaneho repozatara
<pre>git push origin master:master
   resp.
  git push origin phone:phone
</pre>
Pokial git vyhodi nieco ako
<pre>   #  To ssh://<user>@devhome/git/ibanking
   #   &nbsp;! [rejected]        master -> master (non-fast forward)
</pre>
tak treba mergnut k sebe remote zmeny:
<pre>git fetch
   git merge origin/master  # POZOR! treba byt v branchi master
</pre>
Dalsia moznost, ako k sebe dostat zmeny je:
<pre>git pull origin master:master</pre>
ale to zvykne casto(vzdy?) vyhadzovat non-fast forward.

== Dalsie uzitocne prikazy ==

zobrazit zoznam brancov

 <code>$ </code>git branch

checkout & prepnut branch

 <code>$ </code>git checkout '''meno_branchu'''

v pripade konfliktov pri pull

 <code>$ </code>git checkout -m # ukazat mergnute verzie
 <code>$ </code>git checkout -2 # ukazat lokalne verzie
 <code>$ </code>git checkout -3 # ukazat vzdialene verzie

rovnako uzitocne moze byt aj vytvaranie aliasov

 <code>$ </code>git config --global alias.co "checkout"

revertne na dany commit, presune se tam, tzn. ten revert nebude jako "revertujici commit" bez -n to udela novy commit

 <code>$ </code>git revert -n f9ab

prida do lokalniho repositare vsechny nove soubory od aktualni urovne (tedy do stage)

 <code>$ </code>git add .

commitne zmeny do lokalniho repositare a branche

 <code>$ </code>git commit -m "je treba zadat popis"

commitne a jeste predtim udela add vsetkych zmeneych suborov (t.j. nove neprida)

 <code>$ </code>git commit -a ...

nacte z "origin repository" branch master a mergne s lokalnimi zmenami v aktualni working copy a branchi (ked nejde, pozret vyssie, ako zrobit fetch/merge).

 <code>$ </code>git pull origin master

pushne data z lokalniho repozitare do zvoleneho branche na "origin repository"

 <code>$ </code>git push origin tb_auth

udelam nejakou haluz a chci se vratit k tomu (pred commiten, file je ve stage), co je v repository, tedy "revertnout" lokalni zmeny

 <code>$ </code>git checkout -- src/tatrabanka/ib/application/services/PaymentsService.as

odzalohuje to stash-u, vrati head na posledni commit; stash je neco jako lokalni zaloha, misto, kam si muzu odlozit zmeny z working directory

 <code>$ </code>git stash save --keep-index

vymazani stash-e

 <code>$ </code>git stash drop

navraceni zmen zpatky do working directory

 <code>$ </code>git stash pop

porovnani souboru (lokalnich zmen) vuci masteru, ale jen tech, ktere se zmenily

 <code>$ </code>git diff --name-status origin/master

chci se vratit na to, co je v masteru, ale predtim je dobre podivat se, o kolik commitu jsem ja vepredu

 <code>$ </code>git log origin/master..
 <code>$ </code>git reset --hard origin/master

smaze commit, ale necha mi lokalne zmenene soubory

 <code>$ </code>git reset --soft HEAD^

statistika remote repository (porovnani s lokalem)

 <code>$ </code>git remote show origin

zobrazi kompletni prehled zmen na danem souboru

 <code>$ </code>git gui blame <file>

export zmien zo stashu do patch fajlu

 <code>$ </code>git stash show -p stash@{0} > Stash0.patch

stiahnutie zmien z ineho remote repozitaru - 1. vytvorenie linky na remote repozitory, 2. pull

 <code>$ </code>git remote add main_tatraib ssh://vas_login@devhome/git/tatraib
 git pull main_tatraib master

pokud chcete nechat urcity soubor v repozitari, ale nechcete trackovat zmeny, pouzijte nasl. prikaz

 <code>$ </code>git update-index --assume-unchanged <file>

pokud opet chcete sledovat zmeny, provedte

 <code>$ </code>git update-index --no-assume-unchanged <file>

== Jak na ztraceny commit ==

podarilo se mi udelat commit mimo jakykoliv branch. Po checkoutu na tento branch se dany commit stane nedostupnym. Nasledujici prikaz dokaze takovy commit najit

 <code>$ </code>git fsck --lost-found

prikaz vrati neco takoveho:

 [... some blobs omitted ...]
 dangling commit 7c61179cbe51c050c5520b4399f7b14eec943754

Nasledujicim prikazem muzeme rovnez zjistit, ze dany commit existuje:

 <code>$ </code>git reflog

No a nakonec muzeme dany commit mergnout do stavajiciho branche

 <code>$ </code>git merge 7c61179

== Git a submoduly ==

V nekterych pripadech muze byt vhodne rozdelit projekt do vice modulu. Kazdy modul je samostatny git repozitar. Spojuje je jeden rodicovsky repozitar. Vytvoreni vicemoduloveho repozitare je jednoduche a je popsane v manualu. Pro nase prostredi snad jen mala poznamka.

=== Vytvoreni submodulu ===

Prikaz pro vytvoreni submodulu: '''git submodule add'''

Priklad:

 <code>$ </code>git submodule add cesta/k/submodulu/na/serveru nazev

Pri definovani submodulu v repozitari je dobre definovat cestu k nemu relativne, protoze v pripade, ze si nekdo jiny dany repozitar naklonuje, nepujde mu nainicializovat moduly pod jinym uzivatelem.

Konfigurace submodulu se nachazi v souboru:

 .gitmodules

Priklad:

 [submodule "submodule1"]
   path = submodule1    # cesta v ramci rodicovskeho git repozitare
   url = ../submodule1  # cesta k originalnimu (remote) git repozitari

=== Prakticka prace se submoduly ===

S kazdym submodulem se pracuje jako se samostatnym git repozitarem, tzn. vsechny obvykle ukony jako fetch, pull, commit, push apod. se provadeji uplne stejne jako v ostatnich git repozitarich. Rodicovsky git repozitar zmeny v submodulech monitoruje a synchronizuje. Rozlisujeme 3 zakladni prikazy tykajici se submodulu: '''init''', '''update''' a '''sync'''.

Naklonovani git repozitare se submoduly muzeme provest standarnim zpusobem, pote je vsak treba spustit 2 prikazy: '''init''', ktery nainicializuje lokalni konfiguracni soubor a '''update''', ktery fetchne zmeny z remote repozitare do submodulu. Prikaz '''sync''' slouzi pro nacteni konfigurace ze souboru '''.gitmodules''' do konfiguracniho souboru '''.git/config'''.

'''Prikad:'''

 <code>$ </code>git submodule init
 <code>$ </code>git submodule update

V pripade, ze je provedena nejaka zmena v submodulu a je spravne commitnuta a pushnuta, bude o zmenach rodicovsky projekt notifikovany. Zmena se projevi jako zmena v jednom objekte s nazvem submodulu. Commitneme ji jako jakoukoliv jinou zmenu a to '''vzdy po jednom modulu''', ne vice najednou.

'''Priklad:'''

 <code>$ </code>git add submodule1
 <code>$ </code>git commit -m "Updated submodule submodule1."
 <code>$ </code>git push

Od teto chvile budou zmeny v hlavnim git repozitari notifikovane a projekt bude odkazovat na posledni commit v branch master.

'''Poznamka:''' Pokud by zmeny v hlavnim repozitari nebyly zaneseny, tak pri vykonani '''update''' se submodul checkoutne na posledni commit, ktery je v hlavnim repozitari notifikovany a zmeni branch na prazdny! Je treba si na to davat pozor, protoze pokud se provede commit do prazdne branch a potom se branch prepne, budete jej muset hledat (viz kapitola [[GIT howto#Jak_na_ztraceny_commit|Jak na ztraceny commit]]).

== Git 3-way merge<br/> ==

Prehledna dokumentace k mergovani v Gitu je na: [http://git-scm.com/book/en/Git-Branching-Basic-Branching-and-Merging http://git-scm.com/book/en/Git-Branching-Basic-Branching-and-Merging].

V procesu merge existuji 3 zakladni (a jeden vysledny) zdroje souboru:

*The local-checkin that your git tree has: '''LOCAL'''
*The head of the remote repository (that is going to be merged): '''REMOTE'''
*Common ancestor to both LOCAL and REMOTE: '''BASE'''
*The file that will be written as a result: '''MERGED'''.

Poznat nazvoslovi je dulezite zejmena v situacich, kdy je potreba resit konflikty.

'''Priklad''': mame soubor '''''sample.txt''''' a mame checkoutnuty branch ''master'', do ktereho chceme mergnout branch ''test'':
<pre>$ git checkout master
$ git merge test</pre>
V pripade, ze nastane konflikt, Git oznami, pote je dobre pouzit nejaky merge tool, ktery nam pomuze se ve zmenach zorientovat.
<pre>$ git mergetool
merge tool candidates: kdiff3 tkdiff xxdiff meld gvimdiff opendiff emerge vimdiff</pre>
Po merge se vytvori 3 verze souboru '''''sample.txt''''':

#'''sample.txt.BASE''': verze soboru, ktera je predchudcem verzi v mergovanych branchich (muze to byt i prazdny soubor).
#'''sample.txt.LOCAL''': verze souboru v aktualni branch (''master'').
#'''sample.txt.REMOTE''': verze souboru v branchi, kterou mergujeme do ''master'' (''test'').

<br/><br/>

=== Zoznam repozitarov v /git ===

'''ssh://vas_login@devsun/git/...'''

<iframe>
url=http://devhome/git/zoznam_git.pl
width=100%
name=/git
scrolling="yes"
frameborder="1"
</iframe>
<br/><br/>

=== Zoznam repozitarov v /gittb ===

'''ssh://vas_login@devsun/gittb/...'''

<iframe>
url=http://devhome/git/zoznam_gittb.pl
width=100%
name=/gittb
scrolling="yes"
frameborder="1"
</iframe>

=== Zoznam repozitarov v /gitsun ===

'''ssh://vas_login@devsun/gitsun/...'''

<iframe>
url=http://devhome/git/zoznam_gitsun.pl
width=100%
name=/gitsun
scrolling="yes"
frameborder="1"
</iframe>


=== Zoznam repozitarov v cvssrv ===

heslo: ako na ctatrasun

cvssrv:/home/gddsportal/DDSCLIENT

cvssrv:/home/gddsportal/DDSPORTAL

[[Category:GIT]] <br/>[[Category:Prostredia]] <br/> <br/>
