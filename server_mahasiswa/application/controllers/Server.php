<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Server extends CI_Controller {

	function insertMahasiswa () {     
            $nim = $this->input->post('nim');     
            $nama = $this->input->post('name');     
            $jurusan = $this->input->post('majors');
            $jekel = $this->input->post('jekel');
            $email = $this->input->post('email');
            $tambah = array();     
            $tambah ['mahasiswa_nim'] = $nim;     
            $tambah ['mahasiswa_nama'] = $nama;     
            $tambah ['mahasiswa_jurusan'] = $jurusan; 
            $tambah ['mahasiswa_jekel'] = $jekel;
            $tambah ['mahasiswa_email'] = $email;
            $status = $this->db->insert('tb_datamahasiswa', $tambah);     
            $response = array();     
            if ($status == true) {         
                $response['pesan'] = 'insert data berhasil';         
                $response['status'] = 1;     
                
            }     
            else{         
                $response['pesan'] = 'insert data belum berhasil';         
                $response ['status'] = 0;     
                
            }     
            echo json_encode($response); 
            
        }
        
        function getMahasiswa (){     

            //get semua nilai di table     
            $status = $this->db->get('tb_datamahasiswa');     
            if ($status->num_rows() > 0){         
                $response['pesan'] = 'ada data di database';         
                $response['status'] = 1;         
                $response['datanya']= $status->result();     
                
            }     
            else {         
                $response['pesan'] = 'data belum ada di database';         
                $response['status'] = 0;     
                
            }     

            //convert ke json     
            echo json_encode($response); 
            
        } 
        
        function updateMahasiswa(){     
            $id = $this->input->post('id');     
            $nim  = $this->input->post('nim');     
            $nama = $this->input->post('name');     
            $jurusan = $this->input->post('majors');
            $jekel = $this->input->post('jekel');
            $email = $this->input->post('email');
            $this->db->where('mahasiswa_id', $id);     
            $update = array();     
            $update ['mahasiswa_nim'] = $nim;     
            $update ['mahasiswa_nama'] = $nama;     
            $update ['mahasiswa_jurusan'] = $jurusan;     
            $update ['mahasiswa_jekel'] = $jekel;
            $update ['mahasiswa_email'] = $email;
            $status = $this->db->update('tb_datamahasiswa', $update);     

            //check proses update     
            $response = array();     
            if($status == true){         
                $response['pesan'] = 'update mahasiswa berhasil';         
                $response['status'] = 1;     
                
            }     
            else {         
                $response['pesan'] = 'update mahasiswa belum berhasil';         
                $response['status'] = 0;     
                
            }     
            
            echo json_encode($response); 
            
        }
        
        function deleteMahasiswa(){     
            $id = $this->input->post('id');     
            $this->db->where('mahasiswa_id', $id);     
            $status = $this->db->delete('tb_datamahasiswa');     
            
            //check proses delete     
            $response = array();     
            if ($status == true){         
                $response['pesan'] = 'hapus data berhasil';         
                $response['status'] = 1;     
                
            }     
            else {         
                $response['pesan'] = 'hapus data belum berhasil';         
                $response['status'] = 0;     
                
            }     
            
            echo json_encode($response); 
            
        } 
        
}
