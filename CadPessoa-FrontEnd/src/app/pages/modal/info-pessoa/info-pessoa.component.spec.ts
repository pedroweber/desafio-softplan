import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPessoaComponent } from './info-pessoa.component';

describe('InfoTaskComponent', () => {
  let component: InfoPessoaComponent;
  let fixture: ComponentFixture<InfoPessoaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [InfoPessoaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoPessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
