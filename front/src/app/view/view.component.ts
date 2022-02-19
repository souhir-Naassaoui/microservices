import { MembresService } from './../membres.service';
import { Member } from './../model/Member.model';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { AuthenticationService } from '../authentication.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  id: number=0;
  member!:any;
  urlPhoto!:any;
  urlPhotoEtudiants:any="http://localhost:8087/etudiants/photoEtudiant/";;
  etudiants!:any;
  selectedFiles?: FileList;
  currentFile?: File;
  message = '';
  errorMsg = '';
  username?:any;
  type?:any;
  title:any;
  publications:any;
  urlpub:any;

  constructor(private route: ActivatedRoute,private memberService:MembresService,
    private secService:AuthenticationService) {
 
   }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']; 
    console.log(this.id);
    this.username=this.secService.currentUsername;
    this.urlPhoto="http://localhost:8087/enseignants/photoEnseignant/"+this.id;
    
    this.memberService.getPublications(this.id).subscribe(data=>{
      this.publications=data;
      console.log(this.publications);
    })
    this.urlpub="http://localhost:8082/publications/publicationsById/"+this.id+"?name=";
    this.memberService.getEnseignant(this.id).subscribe(data=>{
      this.member=data;
      this.etudiants=this.member.etudiants;
      console.log(this.member);
    })
    
  }

isMe(){
  return this.username==this.member.username;
}

  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
  }

  upload(): void {
    this.errorMsg = '';

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.memberService.uploadEnseignant(this.currentFile,this.id).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              console.log(Math.round(100 * event.loaded / event.total));

            } else if (event instanceof HttpResponse) {
              this.message = event.body.responseMessage;
            }
          },
          (err: any) => {
            console.log(err);

            if (err.error && err.error.responseMessage) {
              this.errorMsg = err.error.responseMessage;
            } else {
              this.errorMsg = 'Error occurred while uploading a file!';
            }

            this.currentFile = undefined;
          });
      }

      this.selectedFiles = undefined;
    }
  }

  uploadPublication(): void {
    this.errorMsg = '';

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.memberService.uploadPublication(this.currentFile,this.id,this.title).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              console.log(Math.round(100 * event.loaded / event.total));

            } else if (event instanceof HttpResponse) {
              this.message = event.body.responseMessage;
            }
          },
          (err: any) => {
            console.log(err);

            if (err.error && err.error.responseMessage) {
              this.errorMsg = err.error.responseMessage;
            } else {
              this.errorMsg = 'Error occurred while uploading a file!';
            }

            this.currentFile = undefined;
          });
      }

      this.selectedFiles = undefined;
    }
  }

}
