import { AuthenticationService } from './../authentication.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MembresService } from '../membres.service';

@Component({
  selector: 'app-view-etudiant',
  templateUrl: './view-etudiant.component.html',
  styleUrls: ['./view-etudiant.component.css']
})
export class ViewEtudiantComponent implements OnInit {

  id: number=0;
  member!:any;
  urlPhoto!:any;
  urlPhotoEtudiants:any="http://localhost:8087/etudiants/photoEtudiant/";;
  encadrant!:any;
  selectedFiles?: FileList;
  currentFile?: File;
  message = '';
  errorMsg = '';
  username?:any;
  type?:any;

  constructor(private route: ActivatedRoute,private memberService:MembresService,
    private secService:AuthenticationService) {
 
   }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']; 
    console.log(this.id);
    this.username=this.secService.currentUsername;
    this.urlPhoto="http://localhost:8087/etudiants/photoEtudiant/"+this.id;
    this.memberService.getEtudiant(this.id).subscribe(data=>{
      this.member=data;
      
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

        this.memberService.uploadEtudiant(this.currentFile,this.id).subscribe(
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
