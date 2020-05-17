# ITBC_Projekat_2

Testira se sajt https://sandbox.2checkout.com/sandbox.

Za rad sa elementima koji se nalaze na web stranici koristiti Selenium, a za pisanje testova TestNG. Primeniti Page Object Model.
Potrebno je da se svi korisceni lokatori cuvaju u jednom tekstualnom fajlu, tako da ukoliko se neki (na primer xpath) promeni citav kod nije potrebno kompajlirati.
Odraditi sledece zahteve:
- Testirati da li je moguce ulogovati se ukoliko je preskocen korak registracije korisnika (da li se može prijaviti koristeci podatke koji nikada nisu sacuvani u bazi korisnika).
- 
- Testirati da li radi forma za registraciju unosom podataka za jednog korisnika.

- Detaljno proveriti da li je moguce registrovati se bez unosa svih polja.

- 
- Pronaci kako da se automatski izgenerise set podataka, i kreirati xlsx ili xls fajl koji je popunjen podacima potrebnim za registraciju 30 korisnika.

- Registrovati 30 osoba, pri cemu se podaci o svakoj osobi citaju iz xlsx ili xls fajla. Za svaku osobu proveriti da li je registracija bila uspesna.

- Testirati logovanje korisnika ukoliko to nije uraðeno prethodnim koracima.

- Testirati dodavanje 5 proizvoda - potrebno je popuniti samo osnovne podatke. (Potrebne podatke ucitati iz xlsx ili xls fajla, kreirati proizvode i proveriti da li je njihovo kreiranje uspesno).

- Povecati cenu prethodno kreiranih proizvoda za 100 i proveriti uspesnost izmena.

- Napisati Bug Report i popuniti ga koristeci informacije pronadjene automatskim i manuelnim testiranjem.




Bonus:
- Napraviti jedan test suite pomocu koga ce se pokrenuti testovi iz svih TestNG klasa.
- Umesto Java projekta, napraviti Maven projekat.





