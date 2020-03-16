import { Component, OnInit, Injectable, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CreatePessoaComponent } from "../modal/create-pessoa/create-pessoa.component";
import { InfoPessoaComponent } from '../modal/info-pessoa/info-pessoa.component'

import { PessoaServiceService } from '../../api/pessoa-service.service';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: "root"
})

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class MainPageComponent implements OnInit {

  pessoas: Array<any>;

  constructor(private modalService: NgbModal, private service: PessoaServiceService, private cd: ChangeDetectorRef) { }

  faPlusCircle = faPlusCircle;

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  ngOnInit() {
    this.pessoas = new Array<any>();

    this.updatePessoasList();

    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true };
    this.dtTrigger.next();

  }

  updatePessoasList() {
    this.service.listar()
      .subscribe(resposta => this.pessoas = resposta);
    this.cd.detectChanges();
    if(this.pessoas ==null){
      this.pessoas = new Array<any>();
    }
    
  }

  openCreatePessoa() {
    const modalRef = this.modalService.open(CreatePessoaComponent);

    modalRef.result.then(() => {
      this.updatePessoasList();
    });
  }

  openInfoPessoa(pessoaSelected: any) {
    const modalRef = this.modalService.open(InfoPessoaComponent);
    modalRef.componentInstance.pessoaSelected = pessoaSelected;

    modalRef.result.then(() => {
      this.updatePessoasList();
    });
  }

 

}
